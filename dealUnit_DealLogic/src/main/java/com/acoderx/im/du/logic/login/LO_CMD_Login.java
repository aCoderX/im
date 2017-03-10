package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.Test;
import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.redis.RedisKey;
import com.acoderx.im.redis.RedisKeyUserInfo;

import java.util.Map;


public class LO_CMD_Login extends MessageDeal {
	private RedisOps redisOps;
	public LO_CMD_Login(){
		redisOps = new RedisOps();
	}
	public DataPacketInner deal(DataPacketInner req){
		//String request = new String(req.getBody(),"UTF-8");
		DataPacket dp = req.getMessage();

		/*String data[] = req.split("\t");
		String s[] = data[3].split("\\|");*/
		//Test redis = new Test();
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
		//System.out.println(userInfo.toString());
		//String result = data[0]+"\t"+data[2]+"\t"+data[1]+"\t"+userInfo.toString();
		//String result = userInfo.toString();

		return dpiAck;
	}
}
