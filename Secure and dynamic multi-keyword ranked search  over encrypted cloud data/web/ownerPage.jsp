<%-- 
    Document   : ownerPage
    Created on : 27 Nov, 2017, 2:51:14 PM
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
                    <li><a href="#">Home</a></li>
                    <li><a href="ownerDetails.jsp">View Owner Details</a></li>
                    <li><a href="ranking.jsp">Ranking</a></li>
                    <li><a class="active" href="ownerPage.jsp">Upload Data</a></li>
                    <li><a href="template.jsp">Sign Out</a></li>
                </ul>
            </nav>
        </header>


        <div class="maincontent">
            <div class="content">

                <article class="topcontent">
                   
                    <content>
                        <form name="upload" action="upload_file.jsp" method="POST" enctype="multipart/form-data">
                        <table>
                    <tr>
                        <td colspan="2"><h1>UPLOAD THE FILE</h1></td>
                    </tr>
                    

                    <tr>
                        <td>UserName</td>
                        
                        <td><input type="text" name="uname" value=""></td>
                    </tr>
                    <tr>
                        <td>Key Words</td>
                        
                        <td><input type="text" name="appkey" value=""></td>
                    

                    <tr>
                        <td><label>Choose the file To Upload</label></td>
                        
                        <td><input name="file_data" type="file" ></td>
                    </tr>
                    <tr>
                        
                        <td><input TYPE="reset" VALUE="Reset"></td>
                        <td>
                            <input TYPE="submit" VALUE="Upload" name="upload" >
                        </td>
                    </tr>
                </table>
                        </form>
                    </content>
                </article>

            </div>
        </div>


        <footer class="mainfooter">
            <p>Secure Data Sharing in Real Cloud</a></p>
        </footer>
    </body>
</html>

