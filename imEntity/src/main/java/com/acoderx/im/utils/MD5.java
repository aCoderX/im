package com.acoderx.im.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xudi on 17-4-22.
 */
public class MD5 {
    private final static String SALT="JBDkfnnfJHbSjVXI";
    public static String encoderByMd5(String str){
        String newstr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            newstr =base64en.encode(md5.digest(str.getBytes("utf-8")));
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }

    public static String encoderByMd5WithSalt(String str){
        return encoderByMd5WithSalt(str,null);
    }

    public static String encoderByMd5WithSalt(String str,String salt){
        if(StringUtils.isEmpty(salt)){
            salt=SALT;
        }
        return encoderByMd5(str+salt);
    }
    public static void main(String args[]){
        System.out.println(encoderByMd5("admin"));
        System.out.println(encoderByMd5WithSalt("admin"));
    }
}
