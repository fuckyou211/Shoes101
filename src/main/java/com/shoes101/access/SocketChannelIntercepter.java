package com.shoes101.access;

import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.Admin;
import com.shoes101.pojo.User;
import com.shoes101.redis.AdminKey;
import com.shoes101.redis.OnlineClientKey;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocketChannelIntercepter implements ChannelInterceptor {

    @Autowired
     RedisService redisService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    //下述说的消息是客户端stomp发送的
    /**
     * 在消息发送之前调用
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message;
    }
    /**
     *
     * 在消息发送之后立即调用
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
        if(stompHeaderAccessor.getCommand()==null) return;
        switch (stompHeaderAccessor.getCommand()){
            case CONNECT:
                connect(stompHeaderAccessor);
                break;
            default:
                break;
        }
    }


    /**
     *
     * 在完成发送之后调用
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
    }

    //上线，存进redis
    private void connect(StompHeaderAccessor stompHeaderAccessor) {
       String token  = stompHeaderAccessor.getNativeHeader("tokenInfo").get(0);
        System.out.println(token);
       Admin admin = redisService.get(AdminKey.token,token,Admin.class);
       User user = redisService.get(UserKey.token,token,User.class);
        List<String> onLineAdminTokenList = new ArrayList<String >();
        List<String> onLineUserTokenList = new ArrayList<String>();
        if(admin!=null){
            //System.out.println(redisService);
            if(redisService.exists(OnlineClientKey.getByAdminName,admin.getAdminname())==false){
                /*onLineAdminTokenList.add(token);
                System.out.println(onLineAdminTokenList.toString());*/
                redisService.set(OnlineClientKey.getByAdminName,admin.getAdminname(),admin);
            }
            /*else{
                onLineAdminTokenList =  redisService.get(OnlineClientKey.onlineAdmin,"", true,String.class);
                System.out.println(onLineAdminTokenList.toString());
                if(onLineAdminTokenList.contains(token)==false)
                    onLineAdminTokenList.add(token);
                redisService.set(OnlineClientKey.onlineAdmin,"",onLineAdminTokenList);
            }*/
        }else if(user!=null){
            if(redisService.exists(OnlineClientKey.getByUserName,user.getUsername())==false){
                //onLineUserTokenList.add(token);
                redisService.set(OnlineClientKey.getByUserName,user.getUsername(),user);
            }
            /*else{
                onLineUserTokenList = redisService.get(OnlineClientKey.getByUserName,"", ArrayList.class);
                if(onLineUserTokenList.contains(user))
                    onLineUserTokenList.add(token);
                redisService.set(OnlineClientKey.onlineUser,"",onLineUserTokenList);
            }*/
        }
    }

}
