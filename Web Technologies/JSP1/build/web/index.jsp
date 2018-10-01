 <%-- 
    Document   : index
    Created on : 6 Apr, 2018, 12:56:45 AM
    Author     : saurabh
--%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h1 style="text-align: center">Student Database</h1>
<%
           Date date=new Date(); 
%>
           <a href="insert.jsp">Insert</a><br>
            <a href="delete.html">Delete</a><br>
              <a href="display.jsp">Display</a><br><br>
           <p> Todays date is :<%=date %> </p>
        
    </body>
</html>

