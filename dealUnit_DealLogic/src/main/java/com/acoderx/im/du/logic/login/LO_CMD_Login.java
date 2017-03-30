package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.redis.RedisKeyUserInfo;


public class LO_CMD_Login extends MessageDeal {
	private RedisOps redisOps;
	public LO_CMD_Login(){
		redisOps = new RedisOps();
	}
	public DataPacketInner deal(DataPacketInner req){
		DataPacket dp = req.getMessage();

		String[] subs = dp.getSubField().split("\t");
		String account = subs[0];

		String id = redisOps.opsForHash().get(new RedisKeyUserInfo.UserAccountID(account), account);
		System.out.println("id:"+id);
		DataPacketInner dpiAck = null;
		if(id!=null){
			String username = redisOps.opsForHash().get(new RedisKeyUserInfo.UserInfo(id), RedisKeyUserInfo.userInfo_NAME);
			DataPacket dpAck = new DataPacket("ACK",dp.getCMD(),dp.getTargetId(),id,dp.getRandomNum(),dp.getMsgTime(),username);
			dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);

			//将sessionid存入，userid关联sessionid
			redisOps.opsForSet().add(new RedisKeyUserInfo.UserSessions(id),req.getSessionID());
			//sessionid关联userid
			redisOps.opsForValue().set(new RedisKeyUserInfo.SessionUser(req.getSessionID()),id);
		}
		return dpiAck;
	}
}
