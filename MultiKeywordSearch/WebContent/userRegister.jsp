<%-- 
    Document   : userRegister
    Created on : 27 Nov, 2017, 2:49:14 PM
    Author     : SAJAN
--%>


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
                    <li><a href="template.jsp">Home</a></li>
                    <li><a class="active" href="registration.jsp">Owner Register</a></li>
                    <li><a href="userRegister.jsp">User register</a></li>
                </ul>
            </nav>
        </header>


        <div class="maincontent">
            <div class="content">

                <article class="topcontent">
                   
                    <content>
                        <form action="servlet_userRegister" method="post">
                        <table>
                <tr>
                <caption>User Registration Details</caption>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="uname" placeholder="Enter User name"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" placeholder="Enter Password"></td>
                </tr>
                <tr>
                    <td>E-mail</td>
                    <td><input type="email" name="email" placeholder="Enter Email"></td>
                </tr>
                <tr>
                    <td>Mobile</td>
                    <td><input type="text" name="mobile" placeholder="Enter Mobile No"></td>
                </tr>
                <tr>
                    <td>Date of Birth</td>
                    <td><input type="text" name="dob" placeholder="Enter DOB"></td>
                </tr>
                <tr>
                    <td>Pin code</td>
                    <td><input type="text" name="pincode" placeholder="Enter Pincode"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Register"></td>
                    <td><a href="login.jsp">Sign in</a></td>
                </tr>
                <%
                    if(request.getParameter("msg")!=null)
                    {
                %>
                <tr>
                    <td colspan="2">
                        <% 
                        String msg1=request.getParameter("msg");
                        %>
                        
                        <a href=""><% out.print(msg1);
                        %></a>
                    </td>
                </tr>
                <%}%>               
            </table>
                        </form>
                    </content>
                </article>

            </div>
        </div>

        <footer class="mainfooter">
            <p>Secure Data Sharing in Real Cloud</p>
        </footer>
    </body>
</html>
