<%-- 
    Document   : display
    Created on : 6 Apr, 2018, 1:23:09 AM
    Author     : saurabh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database display</title>
    </head>
    <body>
    <table border="2">
   <tr>
        <td>Roll-no</td>
        <td>Name</td>
        <td>Marks</td>
   </tr>
   <%
   try
   {
       Class.forName("com.mysql.jdbc.Driver");
       String url="jdbc:mysql://localhost:3306/college";
       String username="root";
       String password="root";
       String query="select * from info";
       Connection conn=DriverManager.getConnection(url, username, password);
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       while(rs.next())
       {
            int roll=rs.getInt(1);
            String temp=rs.getString(2);
            int marks1=rs.getInt(3);
   %>
           <tr>
           <td><%= roll %></td>
           <td><%= temp %></td>
           <td><%= marks1 %></td>
           </tr>

   <%
       }
   %>
   </table>
   <%
        rs.close();
        stmt.close();
        conn.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }
    out.println("<br><a href=\"index.jsp\">Go to home</a>");
   %>
</form>`

</body>
</html>