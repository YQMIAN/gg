/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Administrator
 */
public class ConnectJDBC {
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/user_info2?&useSSL=false&serverTimezone=UTC";
    String user="root";
    String password="123456";
    
    public static Connection getConnection(){
         ConnectJDBC co=new ConnectJDBC();
         Connection con=null;
      
        try {
            Class.forName(co.driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("驱动程序加载失败0000");
        }
        
        try {
             con=DriverManager.getConnection(co.url,co.user,co.password);
        } catch (SQLException ex) {
            System.out.println("连接数据库失败");
        }
      
        
        return con;
    };
    
}
