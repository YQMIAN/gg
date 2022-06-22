/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc.ConnectJDBC;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "regist", urlPatterns = {"/regist"})
public class regist extends HttpServlet {

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
         PrintWriter out = response.getWriter();
          String user_id=request.getParameter("user_id");
          String user_password=request.getParameter("user_password");
          String user_sex=request.getParameter("user_sex");
          String user_hobby=request.getParameter("user_hobby");
          Connection con=ConnectJDBC.getConnection();
          String sql="insert into user_basc(user_id,user_password,user_sex,user_hobby) values(?,?,?,?)";
        try {
            
           PreparedStatement pst= con.prepareStatement(sql);
           pst.setString(1, user_id);
           pst.setString(2, user_password);
           pst.setString(3, user_sex);
           pst.setString(4, user_hobby);
          
                 //捕捉执行状态
           int ifSuccess=pst.executeUpdate();
         
            String respon=String.valueOf(ifSuccess)+","+user_id;
           
           out.write(respon);
        
        } catch (SQLException ex) {
            Logger.getLogger(regist.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
