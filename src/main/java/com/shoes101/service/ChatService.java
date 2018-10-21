package com.shoes101.service;

import com.shoes101.vo.ChatMessage;
import com.shoes101.vo.ChatClientVo;
import java.util.List;

/**
 * 功能描述：简单消息模板，用来推送消息
 * @author Vakoe
 */

public interface ChatService {
    public void sendChatMessage(ChatMessage chatMessage);
    public List<ChatClientVo> handleClientList();
}
