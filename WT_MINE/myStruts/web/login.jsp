<%-- 
    Document   : login
    Created on : 15 Apr, 2018, 10:29:40 PM
    Author     : virus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form name="form" action="LACT.do">
            Name<br>
            <input type="text" name="name" required><br><br>
            Password<br>
            <input type="password" name="pass" required><br><br>
            <input type="submit" value="submit" name="submit">
        </form>
    </body>
</html>
