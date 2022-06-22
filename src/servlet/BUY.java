package servlet;

import jdbc.ConnectJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "BUY",urlPatterns = "/BUY")
public class BUY extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String BUY_goods_id=request.getParameter("BUY_goods_id");
        System.out.println(BUY_goods_id);
        String user_id=request.getParameter("user_id");
        Connection con =ConnectJDBC.getConnection();
        String sql="insert into user_orders(goods_id,user_id) values(?,?)";
        try {
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,BUY_goods_id);
            pst.setString(2,user_id);
            int ifsuccess=pst.executeUpdate();
            if(ifsuccess>0){
                out.print("购买成功");
            }
            else{
                out.print("购买失败");
            }

        } catch (SQLException e) {
            System.out.println("sql语句失败");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
