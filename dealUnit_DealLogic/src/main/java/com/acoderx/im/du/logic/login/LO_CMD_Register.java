package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.redis.RedisKeyUserInfo;

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
        String[] subs = dp.getSubField().split("\t");
        String phone = subs[0];
        String name = subs[1];
        String age = subs[2];
        String nextId = redisOps.opsForValue().get(new RedisKeyUserInfo.UserNextID());
        redisOps.opsForHash().put(new RedisKeyUserInfo.UserAccountID(phone),phone,nextId);
        Map<String,String> map = new HashMap<>();
        map.put("username",name);
        map.put("age",age);
        redisOps.opsForHash().putAll(new RedisKeyUserInfo.UserInfo(nextId),map);
        //TODO 中文存在乱码
        StringBuilder subField=new StringBuilder();
        subField.append(nextId);
        subField.append("\t");
        subField.append(name);
        subField.append("\t");
        subField.append(phone);
        subField.append("\t");

        DataPacket dpAck = new DataPacket("ACK",dp.getCMD(),dp.getTargetId(),nextId,dp.getRandomNum(),dp.getMsgTime(),subField.toString());
        DataPacketInner dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);

        //TODO 返回的时候 由于是注册 所以没有session 找不到返回给谁
        return dpiAck;
    }
}
