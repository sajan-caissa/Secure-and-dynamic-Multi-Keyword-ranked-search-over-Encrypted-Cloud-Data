<%-- 
    Document   : download
    Created on : 27 Nov, 2017, 2:31:40 PM
    Author     : SAJAN
--%>



<%@page import="java.io.OutputStream"%>
<%@page import="com.dropbox.core.DbxWriteMode"%>
<%@page import="com.dropbox.DropBoxAccess"%>
<%@page import="com.dropbox.core.DbxClient"%>
<%@ page import="java.io.FileInputStream" %> 
<%@ page import="java.io.BufferedInputStream" %> 
<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %> 


        <% // you can get your base and parent from the database
            //String base = "upload";
            String filename = request.getParameter("filename");
// you can write http://localhost 
            //String filepath = "D://buildingconfidential/download/";
            //BufferedInputStream buf = null;
            OutputStream myOut = null;
            try {
                myOut = response.getOutputStream();
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
//set response headers 
//                response.setContentType("text/pdf");
                //             response.addHeader("Content-Disposition", "attachment; filename=" + filename);
                DbxClient dbxclient = DropBoxAccess.getDbxClient();
                dbxclient.getFile("/"+filename, null, myOut);

                //               int readBytes = 0;
//read from the file; write to the ServletOutputStream 
//                while ((readBytes = buf.read()) != -1) {
                //                  myOut.write(readBytes);
                //            }
            } catch (IOException ioe) {
                throw new ServletException(ioe.getMessage());
            } finally {
//close the input/output streams 
                if (myOut != null) {
                    myOut.close();
                }
            }%> 
