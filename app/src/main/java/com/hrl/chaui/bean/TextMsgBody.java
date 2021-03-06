package com.hrl.chaui.bean;


public class TextMsgBody extends MsgBody {
    private String message;
    private String extra;

    public String getMessage() {
        return message;
    }

    public TextMsgBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public TextMsgBody setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    @Override
    public String toString() {
        return "TextMsgBody{" +
                "message='" + message + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
