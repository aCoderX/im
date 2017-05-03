package com.acoderx.im.du.logic.common;

import com.acoderx.im.du.logic.chat.CH_CMD_Text;
import com.acoderx.im.du.logic.login.LO_CMD_Init;
import com.acoderx.im.du.logic.login.LO_CMD_Login;
import com.acoderx.im.du.logic.login.LO_CMD_Logout;
import com.acoderx.im.du.logic.login.LO_CMD_Register;
import com.acoderx.im.du.logic.message.MG_CMD_Fin;
import com.acoderx.im.du.logic.message.MG_CMD_Sync;
import com.acoderx.im.entity.CMD;

/**
 * Created by xudi on 2017/2/7.
 */
public class MessageDealFactory{
    public static MessageDeal getMessageDeal(String cmd){
        MessageDeal deal = null;
        switch (cmd){
            case CMD.CMD_LOGIN:
                deal = new LO_CMD_Login();
                break;
            case CMD.CMD_LOGOUT:
                deal = new LO_CMD_Logout();
                break;
            case CMD.CMD_TEXT:
                deal = new CH_CMD_Text();
                break;
            case CMD.CMD_INIT:
                deal = new LO_CMD_Init();
                break;
            case CMD.CMD_REGISTER:
                deal = new LO_CMD_Register();
                break;
            case CMD.CMD_FIN:
                deal = new MG_CMD_Fin();
                break;
            case CMD.CMD_SYNC:
                deal = new MG_CMD_Sync();
                break;
        }
        return deal;
    }

}
