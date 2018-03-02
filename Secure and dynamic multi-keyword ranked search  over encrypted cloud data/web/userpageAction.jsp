<%-- 
    Document   : userpageAction
    Created on : 27 Nov, 2017, 2:48:43 PM
    Author     : SAJAN
--%>

<%@ page import ="java.sql.*" %>
<%
String userid = request.getParameter("uname");
String pwd = request.getParameter("password");
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enablingkeyword_search","root", "root");
Statement st = con.createStatement();
ResultSet rs;
rs= st.executeQuery("select * from registration where username='" + userid + "' and password='" + pwd + "'");
if (rs.next()) {
    String id=rs.getString("id");
session.setAttribute("username", userid);
session.setAttribute("uid", id);
response.sendRedirect("user_pages.jsp");
} else {
response.sendRedirect("login.jsp?msg=invalid login!..");
}
%>
