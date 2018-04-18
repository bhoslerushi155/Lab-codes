/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
/**
 *
 * @author virus
 */
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    private static final String FAILURE = "failure";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm lf=(LoginForm)form;
        
        String name=lf.getName();
        String pass=lf.getPass();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/auth" , "root" , "root");
            
            PreparedStatement ps=con.prepareStatement("select pass from valid where name=?;");
            ps.setString(1, name);
            
            ResultSet rs=ps.executeQuery();
            
           while(rs.next()){
               String tmp=rs.getString(1);
               System.out.println(tmp);
               if(tmp.equals(pass)){
                   return mapping.findForward(SUCCESS);
               }
           }
            return mapping.findForward(FAILURE);
        }catch(Exception e){
             
            return mapping.findForward(FAILURE);
        }
        
       
    }
}
