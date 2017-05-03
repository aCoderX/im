package com.acoderx.im.utils;

import java.util.Collection;

/**
 * Created by xudi on 17-5-3.
 */
public class CollectionUtils {
    public static boolean isNotEmpty(Collection collection){
        if(collection!=null && collection.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
