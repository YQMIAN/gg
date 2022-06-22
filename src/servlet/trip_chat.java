package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ServerEndpoint("/trip_chat")
public class trip_chat {
  static List<Session> users=Collections.synchronizedList(new ArrayList<Session>());

     @OnOpen
    public void strat(Session session){
         users.add(session);
         System.out.println("sessionID为"+session.getId()+"的用户进入了聊天界面--------------");
     }
     @OnMessage
    public void message(String message,Session session) throws Exception{
//                   session.setMaxTextMessageBufferSize(100*1024*1024);
         for(Session s:users){
             s.setMaxTextMessageBufferSize(100*1024*1024);
            RemoteEndpoint.Basic remote=s.getBasicRemote();
            remote.sendText(message);
             System.out.println("杨强-----00");
         }
         System.out.println("sessionID为"+session.getId()+"的用户发送了一条消息--------------");
     }
    @OnClose // 该注解修饰的方法将会客户端连接关闭时被激发
    public void end(Session session, CloseReason closeReason)
    {
        // 每当有客户连接关闭时，删除该客户对应的session
        users.remove(session);
        System.out.println("sessionID为"+session.getId()+"的用户离开--------------");
    }
    @OnError // 该注解修饰的方法将会客户端出错时被激发
    public void error(Session session, Throwable throwable)
    {
        // 每当有客户连接出错时，删除该客户对应的session
        users.remove(session);
        System.out.println("sessionID为"+session.getId()+"的用户连接失败-------------");
    }


}
