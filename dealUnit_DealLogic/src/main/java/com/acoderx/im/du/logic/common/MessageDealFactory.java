package com.acoderx.im.du.logic.common;

import com.acoderx.im.du.logic.chat.CH_CMD_Text;
import com.acoderx.im.du.logic.login.LO_CMD_Login;
import com.acoderx.im.du.logic.login.LO_CMD_Logout;

/**
 * Created by xiaobaibai on 2017/2/7.
 */
public class MessageDealFactory{
    public static MessageDeal getMessageDeal(String cmd){
        MessageDeal deal = null;
        if("CMD_LOGIN".equals(cmd)){
            deal = new LO_CMD_Login();
        }else if("CMD_LOGOUT".equals(cmd)){
            deal = new LO_CMD_Logout();
        }else if("CMD_TEXT".equals(cmd)){
            deal = new CH_CMD_Text();
        }
        return deal;
    }

}
