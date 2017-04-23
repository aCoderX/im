package com.acoderx.im.utils;

/**
 * Created by xudi on 17-4-22.
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        if(str!=null&&str.length()>0){
            return false;
        }
        return true;
    }
    public static String subFieldsTostring(String... str){
        StringBuilder sb = new StringBuilder();
        for(String s : str){
            sb.append(s);
            sb.append("\t");
        }
        return sb.toString();
    }
    public static String[] stringToSubfields(String str){
        String[] subs = str.split("\t");
        return subs;
    }
}
