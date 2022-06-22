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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "homepage",urlPatterns = "/homepage")
public class homepage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("user_id");
        response.setCharacterEncoding("utf-8");
       PrintWriter out=response.getWriter();
        Connection con=ConnectJDBC.getConnection();
        String sql="select * from user_basc where user_id="+user_id;
        try {
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet res=pst.executeQuery();
            String info="";
             while(res.next()){
                     info+=res.getString("user_name")+"*"+
                               res.getString("user_sex")+"*"+
                               res.getString("user_id")+"*"+
                               res.getString("user_hobby")+"";
             }
            out.print(info);
        } catch (SQLException e) {
            System.out.println("查询个人信息失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
