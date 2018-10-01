/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author student
 */
public class DB {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String jdbc_url = "jdbc:mysql://localhost:3306/users";
            Connection con = DriverManager.getConnection(jdbc_url, "root", "root");
            return con;
        } catch (Exception ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getUsers() {
        try {
            Connection con = getConnection();
            String s = "<table border='1'>";
            ResultSet rs = con.prepareStatement("select * from user;").executeQuery();
            while (rs.next()) {
                s += "<tr>";
                s += "<td>" + rs.getString(1) + "</td>";
                s += "<td>" + rs.getString(2) + "</td>";
                s += "<td>" + rs.getString(3) + "</td>";
                s += "</tr>";
            }
            s += "</table>";
            return s;
        } catch (SQLException ex) {
            return ex.toString();
        }        
    }
}
