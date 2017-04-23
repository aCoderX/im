package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.CMDType;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.redis.RedisKeyUserInfo;
import com.acoderx.im.utils.MD5;
import com.acoderx.im.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudi on 17-4-17.
 */
public class LO_CMD_Register extends MessageDeal{
    private RedisOps redisOps;
    public LO_CMD_Register(){
        redisOps = RedisOps.getInstance();
    }
    @Override
    public DataPacketInner deal(DataPacketInner req) throws Exception {
        DataPacket dp = req.getMessage();
        String[] subs = StringUtils.stringToSubfields(dp.getSubField());
        String phone = subs[0];
        String name = subs[1];
        String password = subs[2];
        String nextId = redisOps.opsForValue().increment(new RedisKeyUserInfo.UserNextID(),1).toString();
        redisOps.opsForHash().put(new RedisKeyUserInfo.UserAccountID(phone),phone,nextId);
        Map<String,String> map = new HashMap<>();
        map.put("username",name);
        map.put("password", MD5.encoderByMd5WithSalt(password));
        redisOps.opsForHash().putAll(new RedisKeyUserInfo.UserInfo(nextId),map);

        DataPacket dpAck = new DataPacket(CMDType.ACK,dp.getCMD(),dp.getTargetId(),nextId,dp.getRandomNum(),dp.getMsgTime(),StringUtils.subFieldsTostring(nextId,name,phone));
        DataPacketInner dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);

        return dpiAck;
    }
}
