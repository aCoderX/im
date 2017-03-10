package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.data.redis.interf.HashOperation;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.redis.RedisKeyUserInfo;

/**
 * Created by xiaobaibai on 2017/2/20.
 */
public class LO_CMD_Logout extends MessageDeal {
    public DataPacketInner deal(DataPacketInner req) throws Exception {
        RedisOps redisOps = new RedisOps();
        String sessionId = req.getSessionID();
        DataPacket dp = req.getMessage();
        String userId = redisOps.opsForValue().get(new RedisKeyUserInfo.SessionUser(sessionId));
        redisOps.opsForSet().remove(new RedisKeyUserInfo.UserSessions(userId),sessionId);
        redisOps.opsForKey().delete(new RedisKeyUserInfo.SessionUser(sessionId));
        System.out.println("用户"+userId+"下线");
        DataPacketInner dpiAck = null;
        return dpiAck;
    }
}
