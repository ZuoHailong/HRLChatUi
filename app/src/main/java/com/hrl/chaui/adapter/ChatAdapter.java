package com.hrl.chaui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.hrl.chaui.R;
import com.hrl.chaui.activity.ChatActivity;
import com.hrl.chaui.bean.AudioMsgBody;
import com.hrl.chaui.bean.ButtonMsgBody;
import com.hrl.chaui.bean.CommonQuestionListMsgBody;
import com.hrl.chaui.bean.FileMsgBody;
import com.hrl.chaui.bean.ImageMsgBody;
import com.hrl.chaui.bean.MenuFirstLevelMsgBody;
import com.hrl.chaui.bean.MenuSecondLevelMsgBody;
import com.hrl.chaui.bean.Message;
import com.hrl.chaui.bean.MsgBody;
import com.hrl.chaui.bean.MsgSendStatus;
import com.hrl.chaui.bean.MsgType;
import com.hrl.chaui.bean.TextMsgBody;
import com.hrl.chaui.bean.VideoMsgBody;
import com.hrl.chaui.util.GlideUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {


    private static final int TYPE_SEND_TEXT = 1;
    private static final int TYPE_RECEIVE_TEXT = 2;
    private static final int TYPE_SEND_IMAGE = 3;
    private static final int TYPE_RECEIVE_IMAGE = 4;
    private static final int TYPE_SEND_VIDEO = 5;
    private static final int TYPE_RECEIVE_VIDEO = 6;
    private static final int TYPE_SEND_FILE = 7;
    private static final int TYPE_RECEIVE_FILE = 8;
    private static final int TYPE_SEND_AUDIO = 9;
    private static final int TYPE_RECEIVE_AUDIO = 10;
    private static final int TYPE_MENU_FIRST_LEVEL = 20;
    private static final int TYPE_MENU_SECOND_LEVEL = 21;
    private static final int TYPE_CENTER_TEXT_GRAY_BG = 22;
    private static final int TYPE_COMMON_QUESTION_LIST = 24;
    private static final int TYPE_BUTTON = 25;

    private static final int SEND_TEXT = R.layout.item_text_send;
    private static final int RECEIVE_TEXT = R.layout.item_text_receive;
    private static final int SEND_IMAGE = R.layout.item_image_send;
    private static final int RECEIVE_IMAGE = R.layout.item_image_receive;
    private static final int SEND_VIDEO = R.layout.item_video_send;
    private static final int RECEIVE_VIDEO = R.layout.item_video_receive;
    private static final int SEND_FILE = R.layout.item_file_send;
    private static final int RECEIVE_FILE = R.layout.item_file_receive;
    private static final int RECEIVE_AUDIO = R.layout.item_audio_receive;
    private static final int SEND_AUDIO = R.layout.item_audio_send;
    private static final int MENU_FIRST_LEVEL = R.layout.item_menu_first_level;
    private static final int MENU_SECOND_LEVEL = R.layout.item_menu_second_level;
    private static final int CENTER_TEXT_GRAY_BG = R.layout.item_gray_bg;
    private static final int COMMON_QUESTION_LIST = R.layout.item_common_question_list;
    private static final int BUTTON = R.layout.item_button;
    /*
    private static final int SEND_LOCATION = R.layout.item_location_send;
    private static final int RECEIVE_LOCATION = R.layout.item_location_receive;*/


    public ChatAdapter(Context context, List<Message> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<Message>() {
            @Override
            protected int getItemType(Message entity) {
                boolean isSend = entity.getSenderId().equals(ChatActivity.mSenderId);
                if (MsgType.TEXT == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_TEXT : TYPE_RECEIVE_TEXT;
                } else if (MsgType.IMAGE == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_IMAGE : TYPE_RECEIVE_IMAGE;
                } else if (MsgType.VIDEO == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_VIDEO : TYPE_RECEIVE_VIDEO;
                } else if (MsgType.FILE == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_FILE : TYPE_RECEIVE_FILE;
                } else if (MsgType.AUDIO == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_AUDIO : TYPE_RECEIVE_AUDIO;
                } else if (MsgType.MENU_FIRST_LEVEL == entity.getMsgType()) {
                    return TYPE_MENU_FIRST_LEVEL;
                } else if (MsgType.MENU_SECOND_LEVEL == entity.getMsgType()) {
                    return TYPE_MENU_SECOND_LEVEL;
                } else if (MsgType.CENTER_TEXT_GRAY_BG == entity.getMsgType()) {
                    return TYPE_CENTER_TEXT_GRAY_BG;
                } else if (MsgType.COMMON_QUESTION_LIST == entity.getMsgType()) {
                    return TYPE_COMMON_QUESTION_LIST;
                } else if (MsgType.BUTTON == entity.getMsgType()) {
                    return TYPE_BUTTON;
                }
                return 0;
            }
        });
        getMultiTypeDelegate().registerItemType(TYPE_SEND_TEXT, SEND_TEXT)
                .registerItemType(TYPE_RECEIVE_TEXT, RECEIVE_TEXT)
                .registerItemType(TYPE_SEND_IMAGE, SEND_IMAGE)
                .registerItemType(TYPE_RECEIVE_IMAGE, RECEIVE_IMAGE)
                .registerItemType(TYPE_SEND_VIDEO, SEND_VIDEO)
                .registerItemType(TYPE_RECEIVE_VIDEO, RECEIVE_VIDEO)
                .registerItemType(TYPE_SEND_FILE, SEND_FILE)
                .registerItemType(TYPE_RECEIVE_FILE, RECEIVE_FILE)
                .registerItemType(TYPE_SEND_AUDIO, SEND_AUDIO)
                .registerItemType(TYPE_RECEIVE_AUDIO, RECEIVE_AUDIO)
                .registerItemType(TYPE_MENU_FIRST_LEVEL, MENU_FIRST_LEVEL)
                .registerItemType(TYPE_MENU_SECOND_LEVEL, MENU_SECOND_LEVEL)
                .registerItemType(TYPE_CENTER_TEXT_GRAY_BG, CENTER_TEXT_GRAY_BG)
                .registerItemType(TYPE_COMMON_QUESTION_LIST, COMMON_QUESTION_LIST)
                .registerItemType(TYPE_BUTTON, BUTTON);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        setContent(helper, item);
        setStatus(helper, item);
        setOnClick(helper, item);
    }


    private void setStatus(BaseViewHolder helper, Message item) {
        MsgBody msgContent = item.getBody();
        if (msgContent instanceof TextMsgBody
                || msgContent instanceof AudioMsgBody || msgContent instanceof VideoMsgBody || msgContent instanceof FileMsgBody) {
            //只需要设置自己发送的状态
            MsgSendStatus sentStatus = item.getSentStatus();
            boolean isSend = item.getSenderId().equals(ChatActivity.mSenderId);
            if (isSend) {
                if (sentStatus == MsgSendStatus.SENDING) {
                    helper.setVisible(R.id.chat_item_progress, true).setVisible(R.id.chat_item_fail, false);
                } else if (sentStatus == MsgSendStatus.FAILED) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, true);
                } else if (sentStatus == MsgSendStatus.SENT) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, false);
                }
            }
        } else if (msgContent instanceof ImageMsgBody) {
            boolean isSend = item.getSenderId().equals(ChatActivity.mSenderId);
            if (isSend) {
                MsgSendStatus sentStatus = item.getSentStatus();
                if (sentStatus == MsgSendStatus.SENDING) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, false);
                } else if (sentStatus == MsgSendStatus.FAILED) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, true);
                } else if (sentStatus == MsgSendStatus.SENT) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, false);
                }
            } else {

            }
        }

    }

    private void setContent(BaseViewHolder helper, Message item) {
        if (item.getMsgType().equals(MsgType.TEXT)) {
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
            helper.setText(R.id.chat_item_content_text, msgBody.getMessage());
        } else if (item.getMsgType().equals(MsgType.IMAGE)) {
            ImageMsgBody msgBody = (ImageMsgBody) item.getBody();
            if (TextUtils.isEmpty(msgBody.getThumbPath())) {
                GlideUtils.loadChatImage(mContext, msgBody.getThumbUrl(), (ImageView) helper.getView(R.id.bivPic));
            } else {
                File file = new File(msgBody.getThumbPath());
                if (file.exists()) {
                    GlideUtils.loadChatImage(mContext, msgBody.getThumbPath(), (ImageView) helper.getView(R.id.bivPic));
                } else {
                    GlideUtils.loadChatImage(mContext, msgBody.getThumbUrl(), (ImageView) helper.getView(R.id.bivPic));
                }
            }
        } else if (item.getMsgType().equals(MsgType.VIDEO)) {
            VideoMsgBody msgBody = (VideoMsgBody) item.getBody();
            File file = new File(msgBody.getExtra());
            if (file.exists()) {
                GlideUtils.loadChatImage(mContext, msgBody.getExtra(), (ImageView) helper.getView(R.id.bivPic));
            } else {
                GlideUtils.loadChatImage(mContext, msgBody.getExtra(), (ImageView) helper.getView(R.id.bivPic));
            }
        } else if (item.getMsgType().equals(MsgType.FILE)) {
            FileMsgBody msgBody = (FileMsgBody) item.getBody();
            helper.setText(R.id.msg_tv_file_name, msgBody.getDisplayName());
            helper.setText(R.id.msg_tv_file_size, msgBody.getSize() + "B");
        } else if (item.getMsgType().equals(MsgType.AUDIO)) {
            AudioMsgBody msgBody = (AudioMsgBody) item.getBody();
            helper.setText(R.id.tvDuration, msgBody.getDuration() + "\"");
        } else if (item.getMsgType().equals(MsgType.CENTER_TEXT_GRAY_BG)) {
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
            helper.setText(R.id.tvGrayBg, msgBody.getMessage());
        } else if (item.getMsgType().equals(MsgType.BUTTON)) {
            ButtonMsgBody msgBody = (ButtonMsgBody) item.getBody();
            helper.setText(R.id.button, msgBody.getMessage());
        }
    }


    private void setOnClick(BaseViewHolder helper, Message item) {
        MsgBody msgContent = item.getBody();
        if (msgContent instanceof AudioMsgBody) {
            helper.addOnClickListener(R.id.rlAudio);
        } else if (msgContent instanceof MenuFirstLevelMsgBody) {
            helper.addOnClickListener(R.id.tvQuestion1);
            helper.addOnClickListener(R.id.tvQuestion2);
            helper.addOnClickListener(R.id.tvQuestion3);
            helper.addOnClickListener(R.id.tvQuestion4);
            helper.addOnClickListener(R.id.layoutCommonQuestion);
            helper.addOnClickListener(R.id.layoutActivity);
            helper.addOnClickListener(R.id.layoutAfterSale);
        } else if (msgContent instanceof MenuSecondLevelMsgBody) {
            helper.addOnClickListener(R.id.layoutSecondMenu1);
            helper.addOnClickListener(R.id.layoutSecondMenu2);
            helper.addOnClickListener(R.id.layoutSecondMenu3);
            helper.addOnClickListener(R.id.layoutSecondMenu4);
            helper.addOnClickListener(R.id.layoutSecondMenu5);
        } else if (msgContent instanceof ButtonMsgBody) {
            helper.addOnClickListener(R.id.button);
        } else if (msgContent instanceof CommonQuestionListMsgBody) {
            helper.addOnClickListener(R.id.tvCommonQuestion1);
            helper.addOnClickListener(R.id.tvCommonQuestion2);
            helper.addOnClickListener(R.id.tvCommonQuestion3);
            helper.addOnClickListener(R.id.tvCommonQuestion4);
        }
    }

}
