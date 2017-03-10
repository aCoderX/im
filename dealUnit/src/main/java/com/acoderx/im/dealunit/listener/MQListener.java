package com.acoderx.im.dealunit.listener;

import com.acoderx.im.du.logic.common.CO_MQ_Sender;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.du.logic.common.MessageDealFactory;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.DataPacketUtil;
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
    public void onMessage(Message message) {
        //String request = null;
        try {
            /*request = new String(message.getBody(),"UTF-8");
            System.out.println("dealUnit收到"+request);
            String data[] = request.split("\t");
            String s[] = data[3].split("\\|");
            String cmd=data[0];*/
            System.out.println("长度为："+message.getBody().length);
            DataPacketInner dpi = DataPacketUtil.getDataPacketTransform().byteToInnerObject(message.getBody());

            MessageDeal deal = MessageDealFactory.getMessageDeal(dpi.getMessage().getCMD());
            DataPacketInner dpiAck = deal.deal(dpi);
            if(dpiAck!=null){
                System.out.println("查询到结果"+dpiAck);
                CO_MQ_Sender sender = CO_MQ_Sender.getInstance();
                sender.send(dpiAck,"DEALUNIT.TO.EXCHANGE");
            }

            //System.out.println("dealUnit发送"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
