<%-- 
    Document   : success
    Created on : 6 Apr, 2018, 9:36:15 AM
    Author     : jagannath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        login successful!!
        username is:
        <bean:write name="LoginActionFormBean" property="username"/><br>
        email is:
        <bean:write name="LoginActionFormBean" property="email"/><br>
    </body>
</html>
