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

@WebServlet(name = "shoppingCar",urlPatterns ={"/shoppingCar"})
public class shoppingCar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String user_id=request.getParameter("user_id");
            String choice=request.getParameter("choice");
            String shopping_id2=request.getParameter("shopping_id");
            String goods_id2=request.getParameter("goods_id");
        System.out.println(user_id+"-------");
        System.out.println(goods_id2+".0.0.0..0");
        System.out.println(choice);
            if(choice!=null && choice!=""){
                Connection con=ConnectJDBC.getConnection();
                if(choice.equals("del")){

                      String sql2="delete from  shoppingCar where shopping_id="+shopping_id2;
                    try {
                        con.createStatement().execute(sql2);
                    } catch (SQLException e) {
                        System.out.println("购物车操作失败");
                    }

                }

                if(choice.equals("pay")){
                    String sql2="insert into user_orders(goods_id,user_id) values("+goods_id2+","+user_id+")";
                    String sql3="delete from  shoppingCar where shopping_id="+shopping_id2;

                    try {
                        con.createStatement().execute(sql2);
                        con.createStatement().execute(sql3);

                    } catch (SQLException e) {
                        System.out.println("付款失败");
                    }
                }
            }
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
            PrintWriter out=response.getWriter();
            Connection con=ConnectJDBC.getConnection();
            String sql="select * from goods,shoppingCar where shoppingCar.user_id=? and goods.goods_id=shoppingCar.goods_id";
        try {

          PreparedStatement pst=con.prepareStatement(sql);
          pst.setString(1,user_id);
             ResultSet res=pst.executeQuery();
             String responseTXT="";

             while(res.next()){
                 int shopping_id=res.getInt("shopping_id");
                 String goods_id= res.getString("goods_id");
                 float goods_price= res.getFloat("goods_price");
                 String goods_type= res.getString("goods_type");
                 String goods_description= res.getString("goods_description");
                 String goods_imgSrc= res.getString("goods_imgSrc");
                 responseTXT+="shopping_id:"+shopping_id+" ,"+
                         "goods_id:"+goods_id+" ,"+
                         "goods_price:"+goods_price+" ,"+
                         "goods_type:"+goods_type+" ,"+
                         "goods_description:"+goods_description+","+
                         "goods_imgSrc:"+goods_imgSrc+" +";

             }
            responseTXT=responseTXT.substring(0,responseTXT.length()-1);

            out.print(responseTXT);
             } catch (SQLException e1) {
            System.out.println("查询购物车失败");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


