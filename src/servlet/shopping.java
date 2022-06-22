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
import java.sql.ResultSet;
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
@WebServlet(name = "shopping", urlPatterns = {"/shopping"})
public class shopping extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw=response.getWriter();
        Connection con= ConnectJDBC.getConnection();
        String sql="select * from goods";
        try {
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet res=pst.executeQuery();
            String responseTXT="";
            while(res.next()){
                String goods_id= res.getString("goods_id");
                float goods_price= res.getFloat("goods_price");
                String goods_type= res.getString("goods_type");
                String goods_description= res.getString("goods_description");
                String goods_imgSrc= res.getString("goods_imgSrc");
                int goods_sellCount= res.getInt("goods_sellCount");
                responseTXT+="goods_id:"+goods_id+" ,"+
                        "goods_price:"+goods_price+" ,"+
                        "goods_type:"+goods_type+" ,"+
                        "goods_description:"+goods_description+","+
                        "goods_imgSrc:"+goods_imgSrc+" ,"+
                        "goods_sellCount:"+goods_sellCount+" +";
            }
            responseTXT=responseTXT.substring(0,responseTXT.length()-1);

            pw.print(responseTXT);


        } catch (SQLException ex) {
            System.out.println("sql语句执行错误");

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
