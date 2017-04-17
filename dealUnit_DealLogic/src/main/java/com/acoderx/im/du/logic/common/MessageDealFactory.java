package com.acoderx.im.du.logic.common;

import com.acoderx.im.du.logic.chat.CH_CMD_Text;
import com.acoderx.im.du.logic.login.LO_CMD_Init;
import com.acoderx.im.du.logic.login.LO_CMD_Login;
import com.acoderx.im.du.logic.login.LO_CMD_Logout;
import com.acoderx.im.du.logic.login.LO_CMD_Register;

/**
 * Created by xudi on 2017/2/7.
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
        }else if("CMD_INIT".equals(cmd)){
            deal = new LO_CMD_Init();
        }else if("CMD_REGISTER".equals(cmd)){
            deal = new LO_CMD_Register();
        }
        return deal;
    }

}
