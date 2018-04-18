<%-- 
    Document   : insert
    Created on : 13 Apr, 2018, 3:39:39 PM
    Author     : virus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%
    int roll=Integer.parseInt(request.getParameter("roll"));
    String name=request.getParameter("name");
    int marks=Integer.parseInt(request.getParameter("marks"));
    String email=request.getParameter("email");
         Connection con=null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stud" , "root" , "root");
        
        PreparedStatement ps=con.prepareStatement("insert into info values(?,?,?,?)");
        ps.setInt(1, roll);
        ps.setString(2, name);
        ps.setString(3,email);
        ps.setInt(4,marks);
        
        ps.executeUpdate();
        
        System.out.println("Record added successfully...");
    }catch(Exception e){
        System.out.println("cannot connect to database..");
    }finally{
        
        con.close();
    }
    %>
    <p>record added successfully.....</p><br>
    <a href="index.html">go to home</a>
