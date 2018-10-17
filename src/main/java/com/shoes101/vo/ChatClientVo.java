package com.shoes101.vo;

import java.util.ArrayList;
import java.util.List;

public class ChatClientVo {
    private Integer id;
    private String name;
    private Integer isOnline;
    private String status;
    private Integer unReadMessageCount;
    private List<ChatMessage> messageList = new ArrayList<ChatMessage>();
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUnReadMessageCount() {
        return unReadMessageCount;
    }

    public void setUnReadMessageCount(Integer unReadMessageCount) {
        this.unReadMessageCount = unReadMessageCount;
    }

    public List<ChatMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "ChatClientVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isOnline=" + isOnline +
                ", status='" + status + '\'' +
                ", unReadMessageCount=" + unReadMessageCount +
                ", messageList=" + messageList +
                '}';
    }
}
