package com.shoes101.service.impl;


import com.shoes101.mapper.back.AdminMapper;
import com.shoes101.pojo.Admin;
import com.shoes101.redis.OnlineClientKey;
import com.shoes101.redis.RedisService;
import com.shoes101.service.ChatService;
import com.shoes101.vo.ChatClientVo;
import com.shoes101.vo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：简单消息模板，用来推送消息
 * @author Vakoe
 */
@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    @Autowired
    private SimpMessagingTemplate temlate;
    @Autowired
    RedisService redisService;

    @Autowired
    AdminMapper adminMapper;
    @Override
    public void sendChatMessage(ChatMessage chatMessage) {
        if(chatMessage.getForwardStatus().equals("管理员"))
            temlate. convertAndSend( "/queue/AdminId"+chatMessage.getToId(), chatMessage);
        else if(chatMessage.getForwardStatus().equals("用户"))
            temlate.convertAndSend("/queue/UserId"+chatMessage.getToId(),chatMessage);
    }
    @Override
    public List<ChatClientVo> handleClientList(){
        List<Admin> adminList = adminMapper.fingAllAdmin();
        List<String> onLineAdminTokenList = redisService.get(OnlineClientKey.onlineAdmin,"",true,String.class);
        List<ChatClientVo> chatClientList = new ArrayList<ChatClientVo>();
        //从redis拿最近列表

        //若没有，拿管理员列表
        for(Admin admin:adminList){
            ChatClientVo chatClientVo = new ChatClientVo();
            chatClientVo.setId(admin.getAdminid());
            chatClientVo.setName(admin.getAdminname());
            chatClientVo.setStatus("管理员");
            if(redisService.exists(OnlineClientKey.getByAdminName,admin.getAdminname())){
                chatClientVo.setIsOnline(1);
            }
            else{
                chatClientVo.setIsOnline(0);
            }
            chatClientList.add(chatClientVo);
        }
        return chatClientList;
    }
}
