<%-- 
    Document   : update
    Created on : 13 Apr, 2018, 5:45:14 PM
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
        
        PreparedStatement ps=con.prepareStatement("update info set name=? , email=?,marks=? where roll=?");
        ps.setInt(4, roll);
        ps.setString(1, name);
        ps.setString(2,email);
        ps.setInt(3,marks);
        
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

