<%-- 
    Document   : add
    Created on : 6 Apr, 2018, 2:49:50 AM
    Author     : saurabh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<!DOCTYPE html>
<%
    int rollno=Integer.parseInt(request.getParameter("rollno"));
    String name=request.getParameter("name");
    int marks=Integer.parseInt(request.getParameter("marks"));

        try {
            String connectionURL = "jdbc:mysql://host/college";
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver"); 
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","root");
            if(!connection.isClosed())
            {out.println("Successfully connected to " + "MySQL server using TCP/IP...");
               PreparedStatement stmt = connection.prepareStatement("insert into info values(?, ?, ?);");
            stmt.setInt(1, rollno);
            stmt.setString(2, name);
            stmt.setInt(3, marks);
            int n = stmt.executeUpdate();
            out.println("Inserted " + n + " record.<br>"
                    + "<a href=\"index.jsp\">Go to home</a>");
            } 
            connection.close();
            }catch(Exception ex){
            out.println("Unable to connect to database"+ex);
            } 
%>