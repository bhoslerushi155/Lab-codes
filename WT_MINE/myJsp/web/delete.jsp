<%-- 
    Document   : delete
    Created on : 13 Apr, 2018, 5:41:47 PM
    Author     : virus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%
    int roll=Integer.parseInt(request.getParameter("roll"));
    
         Connection con=null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stud" , "root" , "root");
        
        PreparedStatement ps=con.prepareStatement("delete from info where roll=?");
        ps.setInt(1, roll);
       
        
        ps.executeUpdate();
        
        
    }catch(Exception e){
        System.out.println("cannot connect to database..");
    }finally{
        
        con.close();
    }
    %>
    <p>record added successfully.....</p><br>
    <a href="index.html">go to home</a>
