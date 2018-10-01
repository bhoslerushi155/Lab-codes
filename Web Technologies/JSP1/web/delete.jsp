<%-- 
    Document   : delete
    Created on : 6 Apr, 2018, 1:22:42 AM
    Author     : saurabh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<!DOCTYPE html>
<%
    int rollno=Integer.parseInt(request.getParameter("rollno"));
    

        try {
            String connectionURL = "jdbc:mysql://host/college";
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver"); 
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","root");
            if(!connection.isClosed())
            {out.println("Successfully connected to " + "MySQL server using TCP/IP...");
              Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM info WHERE rollno ="+rollno);
            //int n = stmt.executeUpdate();
            out.println("Deleted " + rollno + " record.<br>"
                    + "<a href=\"index.jsp\">Go to home</a>");
            } 
            connection.close();
            }catch(Exception ex){
            out.println("Unable to connect to database"+ex);
            } 
%>
