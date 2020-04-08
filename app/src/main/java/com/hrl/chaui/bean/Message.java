package com.hrl.chaui.bean;


public class Message {

    private String uuid;
    private String msgId;
    private MsgType msgType;
    private MsgBody body;
    private MsgSendStatus sentStatus;
    private String senderId;
    private String targetId;
    private long sentTime;

    public String getUuid() {
        return uuid;
    }

    public Message setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getMsgId() {
        return msgId;
    }

    public Message setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public Message setMsgType(MsgType msgType) {
        this.msgType = msgType;
        return this;
    }

    public MsgBody getBody() {
        return body;
    }

    public Message setBody(MsgBody body) {
        this.body = body;
        return this;
    }

    public MsgSendStatus getSentStatus() {
        return sentStatus;
    }

    public Message setSentStatus(MsgSendStatus sentStatus) {
        this.sentStatus = sentStatus;
        return this;
    }

    public String getSenderId() {
        return senderId;
    }

    public Message setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getTargetId() {
        return targetId;
    }

    public Message setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public long getSentTime() {
        return sentTime;
    }

    public Message setSentTime(long sentTime) {
        this.sentTime = sentTime;
        return this;
    }
}
