package com.acoderx.im.du.logic.common;

import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.LoggerConf;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CO_MQ_Sender {

	private Logger logger = LoggerConf.getLogger(CO_MQ_Sender.class);
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
		logger.info("DealUnit_ENTITY:"+req+ " Send To " + routeKey);
		rabbitTemplate.convertAndSend("Instant_Message",routeKey,req);
	}

}
