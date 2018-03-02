<%-- 
    Document   : ownerDetails
    Created on : 27 Nov, 2017, 2:39:03 PM
    Author     : SAJAN
--%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="db.DBconnection"%>
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
                    <li><a class="active" href="ownerDetails.jsp">View Owner Details</a></li>
                    <li><a href="ranking.jsp">Ranking</a></li>
                    <li><a href="ownerPage.jsp">Upload Data</a></li>
                    <li><a href="template.jsp">Sign Out</a></li>
                </ul>
            </nav>
        </header>


        <div class="maincontent">
            <div class="content">

                <article class="topcontent">
                    
                    <content>
                        
                        <div align="center" style="overflow-x: auto">
        <table>
            <caption><h2>Owner Details:</h2></caption>
            <tr>
                <th>ID</th>
                <th>Owner Name</th>
                <th>Email</th>
                <th>Mobile No</th>
                <th>DOB</th>
                <th>Pin Code</th>
            </tr>
            <%
                
                try
                {
                Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enablingkeyword_search","root", "root");

                java.sql.Statement st=con.createStatement();
                String sql="select * from registration";
                ResultSet rs=st.executeQuery(sql);
                while(rs.next())
                {
            %>
                <tr>
                    <td><%=rs.getString("id")%></td>
                    <td><%=rs.getString("username")%></td>
                    <td><%=rs.getString("email")%></td>
                    <td><%=rs.getString("mobile")%></td>
                    <td><%=rs.getString("dob")%></td>
                    <td><%=rs.getString("pincode")%></td>
                </tr>
           <%
                }
}
catch(Exception ex)
{
out.print(ex);
}
                %>
        </table>
    </div>
                        
                    </content>
                </article>

            </div>
        </div>




        <footer class="mainfooter">
            <p>Secure Data Sharing in Real Cloud</a></p>
        </footer>
    </body>
</html>

