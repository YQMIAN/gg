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

@WebServlet(name = "addShoppingCar",urlPatterns = "/addShoppingCar")
public class addShoppingCar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          String goods_id=request.getParameter("goods_id");
          String user_id=request.getParameter("user_id");
         Connection con=ConnectJDBC.getConnection();
         response.setContentType("text/html;charset=utf-8");
         PrintWriter out=response.getWriter();
         String sql="insert into shoppingCar(goods_id,user_id) values(?,?)";
        try {
            System.out.println("好的");
            PreparedStatement pst=con.prepareStatement(sql);
                             pst.setString(1,goods_id);
                             pst.setString(2,user_id);
                             int ifsuccess=pst.executeUpdate();
                             if(ifsuccess>0){
                                 out.print("添加成功");
                                 System.out.println(ifsuccess+"返回的状态!");

                             }
                             else{
                                 out.print("添加失败");
                             }

        } catch (SQLException e) {
            System.out.println("添加购物车的sql语句执行失败");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
