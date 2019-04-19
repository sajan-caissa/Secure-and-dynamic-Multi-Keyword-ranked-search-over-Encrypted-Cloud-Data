<%-- 
    Document   : multikeywordSearch
    Created on : 27 Nov, 2017, 2:35:16 PM
    Author     : SAJAN
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="db.DBconnection"%>
<%@page import="db.EncryptionDecryptionMechanism"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>  Secure Data Sharing in Real Cloud </title>
    </head>
    <body class="body">
        <header class="mainheader">

            <h1>Privacy-Preserving Multi-Keyword Search on​ Encrypted Outsourced Data</h1>
            <nav>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="userPage.jsp">Keyword Request</a></li>
                    <li><a href="userDetails.jsp">View user Details</a></li>
                    <li><a href="rankinguser.jsp">Ranking</a></li>
                    <li><a class="active" href="multikeywordSearch.jsp">Multi Keyword Search</a></li>
                    <li><a href="template.jsp">Sign Out</a></li>
                </ul>
            </nav>
        </header>


        <div class="maincontent">
            <div class="content">

                <article class="topcontent">

                    <content>

                        <div align="center" style="overflow-x: auto">
                            <form action="" method="">
                                <table>
                                    <caption><h2>Multi Keyword Search</h2></caption>
                                    <tr>
                                        <td>Key word</td>
                                        <td><input type="text" value="" name="keyword"></td>
                                    </tr>
                                    <tr>
                                        <td><input type="reset" value="Reset" name="reset"></td>
                                        <td><input type="submit" value="Request" name="request"></td>
                                    </tr>
                                </table>
                            </form>

                            
                                <%
                                    String keyword = request.getParameter("keyword");
                                    if (keyword != null) {
                                        %>
                                        <table>
                                <tr>
                                    <td>Id</td>
                                    <td>User Name</td>
                                    <td>FileName</td>
                                    <td>Secret Key</td>
                                </tr>
                                        <%
                                        try {
                                        	final String AESKEY = EncryptionDecryptionMechanism.retrieveAESString();
                                        	String [] trapdoors = EncryptionDecryptionMechanism.buildKeyWordValueArray(AESKEY, keyword);
                                        	String singleTrapdoor = EncryptionDecryptionMechanism.makeTrapdoor(AESKEY, keyword);
                                        	
                                           Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enablingkeyword_search?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","bryan", "bryan");

                                            Statement st = con.createStatement();
                                            String sqlQuery0 =  "SELECT * fileupload WHERE id in ( " +
                                                    "SELECT fileuploadid " +
                                                    "FROM   fileupload_filters " +
                                                    "WHERE  trapdoor = ? )";
                                            
                                            /* String sqlQuery =  String.format("SELECT * FROM fileupload WHERE id in (SELECT fileuploadid FROM fileupload_filters WHERE  trapdoor LIKE '%%%s%%' )", singleTrapdoor);

                                            String sqlQuery2 =  String.format("SELECT * FROM fileupload WHERE  appkey LIKE '%%%s%%'", singleTrapdoor); */

                                            
                                            /* String sqlQuery =  "INSERT INTO fileupload_filters  (fileuploadid, trapdoor) " +
                                                    "SELECT ID, ? AS trapdoor " +
                                                    "FROM   fileupload " +
                                                    "WHERE  filename = ? "; */
                                           	String [] queries = keyword.trim().split("\\s+");
                                            for (String currString : queries) {
                                            	String sql = "select * from fileupload where appkey like '%"+ currString + "%'";

                                                // ResultSet rs = st.executeQuery(sqlQuery2);
                                                ResultSet rs = st.executeQuery(sql);
                                         while (rs.next()) {%>
                                    <tr>
                                        <td><%=rs.getString("id")%></td>
                                        <td><%=rs.getString("uname")%></td>
                                        <td><%=rs.getString("filename")%></td>
                                         <td><%=rs.getString("appkey")%></td>
                                         <td><a href="download.jsp?filename=<%=rs.getString("filename")%>">Download</a></td>
                                    </tr>
                                     
                                    <%}
                                            }

                                            
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }%>
                                    </table>
                                <%}
                                
                                else {
                                  
                                    }
                                %>
                           


                        </div>
                    </content>
                </article>

            </div>
        </div>




        <footer class="mainfooter">
            <p>Privacy-Preserving Multi-Keyword Search on​ Encrypted Outsourced Data</p>
        </footer>
    </body>
</html>

