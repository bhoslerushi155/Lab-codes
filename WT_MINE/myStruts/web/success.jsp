<%-- 
    Document   : success
    Created on : 15 Apr, 2018, 10:34:02 PM
    Author     : virus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
    </head>
    <body>
        <h1>Login Successful!</h1><br>
        Name<br>
        <bean:write name="LoginForm" property="name"/><br>
        
    </body>
</html>
