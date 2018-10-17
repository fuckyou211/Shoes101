package com.shoes101.vo;

/*{
        from:"jiba",
                to:'admin',
            message:"吃屎啦你！",
            fromId:"",
            toId:"",
            forwardStatus:
        date:""
    },*/
public class ChatMessage {

   private String from;

   private String to;

   private String msg;

   private String date;

   private Integer fromId;

   private Integer toId;

   private String forwardStatus;

   private String fromStatus;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getForwardStatus() {
        return forwardStatus;
    }

    public void setForwardStatus(String forwardStatus) {
        this.forwardStatus = forwardStatus;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msg='" + msg + '\'' +
                ", date='" + date + '\'' +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", forwardStatus='" + forwardStatus + '\'' +
                ", fromStatus='" + fromStatus + '\'' +
                '}';
    }
}
