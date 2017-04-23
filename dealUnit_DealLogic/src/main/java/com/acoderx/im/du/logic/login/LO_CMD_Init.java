package com.acoderx.im.du.logic.login;

import com.acoderx.im.data.operation.RedisOps;
import com.acoderx.im.du.logic.common.MessageDeal;
import com.acoderx.im.entity.CMDType;
import com.acoderx.im.entity.DataPacket;
import com.acoderx.im.entity.DataPacketInner;
import com.acoderx.im.entity.LoggerConf;
import com.acoderx.im.redis.RedisKeyUserInfo;
import org.slf4j.Logger;

import java.util.Set;

/**
 * Created by xudi on 17-4-16.
 */
public class LO_CMD_Init extends MessageDeal {

    private Logger logger = LoggerConf.getLogger(LO_CMD_Init.class);
    private RedisOps redisOps;
    public LO_CMD_Init(){
        redisOps = RedisOps.getInstance();
    }
    @Override
    public DataPacketInner deal(DataPacketInner req) throws Exception {
        DataPacket dp = req.getMessage();
        String userid = dp.getOriginId();
        Set<String> friends = redisOps.opsForSet().members(new RedisKeyUserInfo.UserFriends(userid));
        logger.info("DEALLOGIC:查询到"+userid+"的好友"+friends);
        DataPacketInner dpiAck;
        DataPacket dpAck ;
        StringBuilder subField=new StringBuilder();
        for (String friend : friends){
            String userName = redisOps.opsForHash().get(new RedisKeyUserInfo.UserInfo(friend),RedisKeyUserInfo.userInfo_NAME);
            subField.append(friend);
            subField.append("|");
            subField.append(userName);
            subField.append("\t");
        }
        dpAck = new DataPacket(CMDType.ACK,dp.getCMD(),dp.getTargetId(),dp.getOriginId(),dp.getRandomNum(),dp.getMsgTime(),subField.toString());
        dpiAck = new DataPacketInner(req.getSessionID(),req.getTargetId(),dpAck);
        return dpiAck;
    }
}
