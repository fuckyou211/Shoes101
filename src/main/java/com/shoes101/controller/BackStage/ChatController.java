package com.shoes101.controller.BackStage;

import com.alibaba.fastjson.JSON;
import com.shoes101.access.AccessInterceptor;
import com.shoes101.access.AdminContext;
import com.shoes101.pojo.Admin;
import com.shoes101.vo.ChatMessage;
import com.shoes101.redis.OnlineClientKey;
import com.shoes101.redis.RedisService;
import com.shoes101.result.CodeMsg;
import com.shoes101.result.Result;
import com.shoes101.service.AdminService;
import com.shoes101.service.ChatService;
import com.shoes101.vo.ChatClientVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ChatController {

    private  final Logger logger= LoggerFactory.getLogger(AccessInterceptor.class) ;

    @Autowired
    private ChatService chatService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AdminService adminService;

    @MessageMapping("/chat")
    public void handleChat(String msg){
        Date date = new Date();
        ChatMessage chatMessage = JSON.parseObject(msg,ChatMessage.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        chatMessage.setDate(simpleDateFormat.format(date));
        System.out.println(chatMessage);
        chatService.sendChatMessage(chatMessage);
    }
    @RequestMapping("/chat/toChatRoom")
    public String toChatRoom(HashMap<String,Object> map)
    {
        Admin admin = AdminContext.getAdmin();
        String adminInfo = JSON.toJSONString(admin);
        map.put("AdminInfo",adminInfo);
        return "/back/chat";
    }

    @RequestMapping("/chat/getClientList")
    @ResponseBody
    public Result<List<?>> getClientList(){
        List<ChatClientVo> list = chatService.handleClientList();
        return Result.success(list);
    }
    //下线
    @RequestMapping("/chat/adminloginOut")
    @ResponseBody
    public Result loginOut(String token){
        if(redisService.delete(OnlineClientKey.onlineAdmin,""))
            return Result.success(1);
        else return Result.error(new CodeMsg(225522,"下线失败"));
    }

    //保存消息记录
}
