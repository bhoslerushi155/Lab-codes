<%-- 
    Document   : display
    Created on : 13 Apr, 2018, 3:51:47 PM
    Author     : virus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<a href="index.html">go to home</a><br>
<%
    
         Connection con=null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stud" , "root" , "root");
        
        PreparedStatement ps =con.prepareStatement("select * from info;");
        ResultSet rs =ps.executeQuery();
        
        %>
        <table border="1">
            <tr>
                <th>Roll No</th>
                <th>Name</th>
                <th>Email</th>
                <th>Marks</th>
            </tr>
        <%
        while(rs.next()){
            int roll=rs.getInt(1);
            String name=rs.getString(2);
            String email=rs.getString(3);
            int marks=rs.getInt(4);
           %>
           <tr>
               <td><%=roll%></td>
               <td><%=name %></td>
               <td><%=email %></td>
               <td><%=marks %></td>     
           </tr> 
           <%
            
            
        }
         %>
         </table>
         <%
        
    }catch(Exception e){
        System.out.println("cannot connect to database..");
    }finally{
        
        con.close();
    }
    %>
   
    
