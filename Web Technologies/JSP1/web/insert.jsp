<%-- 
    Document   : insert.jsp
    Created on : 6 Apr, 2018, 1:22:31 AM
    Author     : saurabh
--%>

<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<html> 
<head> 
<title>Connection with mysql database</title>
</head> 
<body>
<h1>Connection status</h1>
<form action="add.jsp">
Roll-no:<br>
<input type="text" name="rollno" required="required">
<br>
Name:<br>
<input type="text" name="name" required="required">
<br>
Marks<br>
<input type="text" name="marks" required="required">
<br><br>
<input type="submit" value="submit">
</form>

</font>
</body> 
</html>