/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
public class s4 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html><body>");
        pw.println("<form method='post'>");
        pw.println("Enter name to search:\t<input type='name' name='name'/><br>");        
        pw.println("<input type='submit' value='Search'/>");
        pw.println("</form>");
        pw.println(DB.getUsers());
        pw.println("</body></html>");
        pw.close();

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            response.setContentType("text/html");            
            
            String name = request.getParameter("name");
            
            Connection con = DB.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from user where name=?");            
            stmt.setString(1, name);            
            pw.println("Search results:");
            String s = "<table border='1'>";
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                s += "<tr>";
                s += "<td>" + rs.getString(1) + "</td>";
                s += "<td>" + rs.getString(2) + "</td>";
                s += "<td>" + rs.getString(3) + "</td>";
                s += "</tr>";
            }
            s += "</table>";
            pw.println(s
                    + "<a href=\"index.html\">Go to home</a>");            
        } catch (Exception ex) {
            pw.println(ex);
        }
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
