<%-- 
    Document   : userPage
    Created on : 27 Nov, 2017, 2:48:05 PM
    Author     : SAJAN
--%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="db.DBconnection"%>
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

            <h1> Secure Data Sharing in Real Cloud</h1>
            <nav>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a class="active" href="userPage.jsp">Keyword Request</a></li>
                    <li><a href="userDetails.jsp">View user Details</a></li>
                    <li><a href="rankinguser.jsp">Ranking</a></li>
                    <li><a href="multikeywordSearch.jsp">Multi Keyword Search</a></li>
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
                                    <caption><h2>Request Key Word</h2></caption>
                                    <tr>
                                        <td>Key Word</td>
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
                                    <td>FileName</td>
                                    <td>Index</td>
                                </tr>
                                        <%
                                        try {
                                            //Connection con = DBconnection.getConnection();
                                            
                                            Class.forName("com.mysql.jdbc.Driver");
                                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enablingkeyword_search","root", "root");

                                            Statement st = con.createStatement();

                                            String sql = "select * from fileupload where appkey='" + keyword + "'";
                                            ResultSet rs = st.executeQuery(sql);
                                     while (rs.next()) {%>
                                <tr>
                                    <td><%=rs.getString("id")%></td>
                                    <td><%=rs.getString("filename")%></td>
                                     <td><%=rs.getString("indexval")%></td>
                                     <td><a href="download.jsp?filename=<%=rs.getString("filename")%>">Download</a></td>
                                </tr>
                                 
                                <%}
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
            <p>Secure Data Sharing in Real Cloud</p>
        </footer>
    </body>
</html>
