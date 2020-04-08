package com.hrl.chaui.bean;

/**
 * Created by ZuoHailong on 2020/4/7.
 */
public class ButtonMsgBody extends MsgBody {
    private String message;
    private String extra;

    public String getMessage() {
        return message;
    }

    public ButtonMsgBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public ButtonMsgBody setExtra(String extra) {
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
