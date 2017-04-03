package com.acoderx.im.dealunit.listener;

import com.acoderx.im.du.logic.common.CO_MQ_Sender;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.du.logic.common.MessageDealFactory;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.DataPacketUtil;
import com.acoderx.im.entity.LoggerConf;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static com.acoderx.im.entity.DataPacketUtil.getDataPacketTransform;


/**
 * Created by xiaobaibai on 2016/12/28.
 */
@Component
public class MQListener implements MessageListener{
    private Logger logger = LoggerConf.getLogger(MQListener.class);
    public void onMessage(Message message) {
        try {
            DataPacketInner dpi = DataPacketUtil.getDataPacketTransform().byteToInnerObject(message.getBody());
            logger.info("DEALUNIT:"+"接受到"+ dpi);
            MessageDeal deal = MessageDealFactory.getMessageDeal(dpi.getMessage().getCMD());
            DataPacketInner dpiAck = deal.deal(dpi);
            if(dpiAck!=null){
                CO_MQ_Sender sender = CO_MQ_Sender.getInstance();
                sender.send(dpiAck,"DEALUNIT.TO.EXCHANGE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
