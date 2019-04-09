<%-- 
    Document   : upload_file
    Created on : 27 Nov, 2017, 2:46:44 PM
    Author     : SAJAN
--%>


<%@page import="com.dropbox.core.v1.DbxWriteMode"%>
<%@page import="com.dropbox.DropBoxAccess"%>
<%@page import="com.dropbox.core.v2.DbxClientV2"%>
<%@page import="db.EncryptionDecryptionMechanism"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.crypto.SecretKey"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            // Verify the content type
           int value=(int) (Math.random() * 55);
           String indexval=Integer.toString(value);
            File file;
            String filePath = "/home/whoami/upload/";
            
            final String AESKEY = EncryptionDecryptionMechanism.retrieveAESString(); 
            
            
            
            
            /* String filePath = "E:\\2017\\JAVA\\upload\\"; */
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(5000 * 1024);
                // Location to save data that is larger than maxMemSize.
                //factory.setRepository(new File("c:\\"));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax(5000 * 1024);
                
                String username, appkey, filename, filepath, fsize;
                String [] trapdoors = null;
                username = appkey = filename = filepath = fsize = "";
                try {
                    // Parse the request to get file items.
                    List<FileItem> fileItems = upload.parseRequest(request);
                    Iterator<FileItem> it = fileItems.iterator();

                    while (it.hasNext()) {
                        FileItem item = (FileItem) it.next();
                        long FileSize = item.getSize();
                        

                        if (item.isFormField()) {
                            //Plain request parameters will come here. 
                            String name = item.getFieldName();

                            if ("uname".equals(name)) {
                                username = item.getString();
                            } else if ("appkey".equals(name)) {
                                appkey = item.getString();
                                appkey = EncryptionDecryptionMechanism.encryptKeyWord(AESKEY, appkey);
                                
                                trapdoors = EncryptionDecryptionMechanism.buildKeyWordValueArray(AESKEY, appkey);
                            }
                        } else {
                            String fieldName = item.getFieldName();
                            if (fieldName.equals("file_data")) {
                                String absolute = item.getName(); // FileName.pdf
                                fsize = String.valueOf(FileSize);

                                String contentTyp = item.getContentType();

                                boolean isInMemory = item.isInMemory();

                                long sizeInBytes = item.getSize();

                                // Write the file
                                if (absolute.lastIndexOf("/") >= 0) {
                                    filename = absolute.substring(absolute.lastIndexOf("/"));
                                } else {
                                    filename = absolute.substring(0);
                                }
                                //file = new File(filePath + filename);
                                //InputStream in = new FileInputStream(file);
                                
                                 // drop box coading start   
                                 String dbxFolder = "privacyproject";
                                DbxClientV2 dbxclient = DropBoxAccess.getDbxClient();
                                 dbxclient.files().upload("/" + dbxFolder + "/" + filename);
                                //dbxclient.uploadFile("/"+filename, DbxWriteMode.add(), -1, item.getInputStream());
                                //out.print(filePath + filename + " <br> ");
                                //item.write(file);
                                //out.println(file);
                                 // drop box coading completed
                            }
                        }
                    }
                    String query = "insert into " + filename + username + fsize + filePath + appkey+indexval;
                    out.print(query);

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enablingkeyword_search?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "bryan", "bryan");
                    String sql = "insert into fileupload (uname,filename,filesize,filePath,appkey,indexval)values (?,?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                   	
                    
                    pst.setString(1, username);
                    pst.setString(2, filename);
                    pst.setString(3, fsize);
                    pst.setString(4, filePath);
                    pst.setString(5, appkey);
                    pst.setString(6, indexval);
                    int i = pst.executeUpdate();
                    int j = -1;
                    
                    out.println(pst + "<br>");
                    if (i > 0 ) {
                    	// String nestedSql = String.format("select CAST(id AS UNSIGNED) from fileupload where filename = ?", filename);
                    	for (String trapdoor : trapdoors) {
                    		String sqlQuery =  "INSERT INTO fileupload_filters  (fileuploadid, trapdoor) " +
                                    "SELECT ID, ? AS trapdoor " +
                                    "FROM   fileupload " +
                                    "WHERE  filename = ? ";
                    		
                        	//String sql2 = "insert into fileupload_filters(fileuploadid, trapdoor) values(select id from fileupload where filename = ?,?)";
                            PreparedStatement pst2 = con.prepareStatement(sqlQuery);
                            
                            pst2.setString(1,trapdoor);
                            pst2.setString(2,filename);
                            
                            j = pst2.executeUpdate();
                            out.println(pst2 + "<br>");
                        }                        
                    }
                    
                    if (j > 0) {
                    	response.sendRedirect("ownerPage.jsp");
                    } else {
                        response.sendRedirect("template.jsp");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        %>
    </body>
</html>
