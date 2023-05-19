<%-- 
    Document   : login
    Created on : 27 Nov, 2017, 2:34:07 PM
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
                    
                    <li><a class="active" href="login.jsp">User</a></li>
                    <li><a href="registration.jsp">Register</a></li>
                </ul>
            </nav>
        </header>


        <div class="maincontent">
            <div class="content">

                <article class="topcontent">
                   <form action="login_action.jsp" method="post">
            <table>
                <tr>
                    <th colspan="2">Sign In</th>
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
                    <td>Select User</td>
                    <td>
                        <select name="loginoption">
                            <option value="volvo">Owner Login</option>
                            <option value="saab">User Login</option>
                           
                        </select>
                        
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"></td>
                    <td><a href="registration.jsp">Account Register</a></td>
                </tr>
                <% if(request.getParameter("msg")!=null){%>
                <tr>
                    
                    <td colspan="2">
                        <%
                            String msg1=request.getParameter("msg");
                            out.print(msg1);
                        %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            </center> 

                    <content>
                        
                    </content>
                </article>

            </div>
        </div>




        <footer class="mainfooter">
            <p>Secure Data Sharing in Real Cloud</p>
        </footer>
    </body>
</html>
