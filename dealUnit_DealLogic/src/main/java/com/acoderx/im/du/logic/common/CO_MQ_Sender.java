package com.acoderx.im.du.logic.common;

import com.acoderx.im.entity.DataPacketInner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CO_MQ_Sender {

	private RabbitTemplate rabbitTemplate;

	private CO_MQ_Sender() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dealLogic-application.xml");
		rabbitTemplate = (RabbitTemplate) applicationContext.getBean("amqpTemplate");
	}

	private static CO_MQ_Sender mqSender = new CO_MQ_Sender();

	public static CO_MQ_Sender getInstance() {
		return mqSender;
	}

	public void send(DataPacketInner req, String routeKey) {
		//logger.info("         >>>>>>>>>DealUnit " + dealUnit + " Send To " + routeKey + ":" + dpi.toString());
		//sendTo(dpi, routeKey);
		System.out.println("发送"+req+"到"+routeKey);
		rabbitTemplate.convertAndSend("Instant_Message",routeKey,req);
	}

}
