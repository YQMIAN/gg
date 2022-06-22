/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        String user_id = request.getParameter("account");
        String user_password = request.getParameter("password");
        String sql="";
        Connection con = ConnectJDBC.getConnection();
        ResultSet res = null;
        String responseString = "";
        try {
            Statement st = con.createStatement();
            sql = "select * from user_basc where user_id=" + user_id + " and user_password=" + user_password + ";";
         
            res = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("sql语句执行错误");
        }

        try {
            if (res.next()) {
               
                responseString = "{\"ifRight\":true" + ",\"user_id\":" + user_id + "}";
                out.write(responseString);
            } else {
                responseString = "{\"ifRight\":false}";
                out.write(responseString);
            }
        } catch (Exception ex) {
            System.out.println("读取resultQuery查询结果失败");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
