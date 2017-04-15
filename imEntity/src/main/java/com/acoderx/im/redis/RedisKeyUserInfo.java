package com.acoderx.im.redis;

/**
 * Created by xudi on 2017/2/12.
 */
public class RedisKeyUserInfo {
    public static String userInfo_ACC="account";
    public static String userInfo_NAME="username";


    public static class UserInfo extends RedisKeyClass{
        public UserInfo(String userid){
            this.name = "USER:"+userid+":INFO";
            this.sync = true;
            this.type = RedisDataType.HASH;
        }
    }

    public static class UserAccountID extends RedisKeyClass{
        public UserAccountID(String account){
            this.name = "SYS:ACC:"+account;
            this.sync = false;
            this.type = RedisDataType.HASH;
        }
    }

    public static class UserSessions extends RedisKeyClass{
        public UserSessions(String userid){
            this.name = "USER:"+userid+":SESSION";
            this.sync = false;
            this.type = RedisDataType.SET;
        }
    }

    public static class SessionUser extends RedisKeyClass{
        public SessionUser(String sessionId){
            this.name = "SESSION:"+sessionId+":USER";
            this.sync = false;
            this.type = RedisDataType.STRING;
        }
    }
}
