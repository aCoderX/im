package com.acoderx.im.du.logic.common;

import com.acoderx.im.entity.DataPacketInner;

/**
 * Created by xiaobaibai on 2017/2/7.
 */
public abstract class MessageDeal {
    public abstract DataPacketInner deal(DataPacketInner req) throws Exception;
}
