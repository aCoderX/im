package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.callback.MySQLOperations;
import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.du.logic.error.LoginError;
import com.acoderx.im.entity.CMDType;
import com.acoderx.im.entity.Constants;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.redis.RedisKeyUserInfo;
import com.acoderx.im.utils.MD5;
import com.acoderx.im.utils.StringUtils;


public class LO_CMD_Login extends MessageDeal {
	private RedisOps redisOps;
	public LO_CMD_Login(){
		redisOps = RedisOps.getInstance();
	}
	public DataPacketInner deal(DataPacketInner req){
		DataPacket dp = req.getMessage();

		String[] subs = dp.getSubField().split("\t");
		String account = subs[0];
		String pass = subs[1];

		String id = redisOps.opsForHash().get(new RedisKeyUserInfo.UserAccountID(account), account);
		DataPacketInner dpiAck = null;
		if(id!=null){
			String username = redisOps.opsForHash().get(new RedisKeyUserInfo.UserInfo(id), RedisKeyUserInfo.userInfo_NAME);
			String password = redisOps.opsForHash().get(new RedisKeyUserInfo.UserInfo(id), RedisKeyUserInfo.userInfo_PASS);
			if(password.equals(MD5.encoderByMd5WithSalt(pass))){
				//查询sync值
				Integer syncNo = MySQLOperations.getInstance().getCacheMessageDao().countSycnNOBySender(Integer.valueOf(id));
				syncNo=(syncNo==null?1:syncNo+1);
				DataPacket dpAck = new DataPacket(CMDType.ACK,dp.getCMD(),dp.getTargetId(),id,dp.getRandomNum(),dp.getMsgTime(), StringUtils.subFieldsTostring(Constants.SUCCESS,username,syncNo.toString()));
				dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);
				//将sessionid存入，userid关联sessionid
				redisOps.opsForSet().add(new RedisKeyUserInfo.UserSessions(id),req.getSessionID());
				//sessionid关联userid
				redisOps.opsForValue().set(new RedisKeyUserInfo.SessionUser(req.getSessionID()),id);
				return dpiAck;
			}
		}
		dpiAck = LoginError.wrongAccontOrPass(req);
		return dpiAck;
	}
}
