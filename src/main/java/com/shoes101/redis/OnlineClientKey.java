package com.shoes101.redis;

public class OnlineClientKey extends BasePrefix{

    public OnlineClientKey(String prefix) {
        super(prefix);
    }

    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    /**
     *
     * @param expireSeconds  有效时间
     * @param prefix  key前缀
     */
    public OnlineClientKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static OnlineClientKey onlineAdmin = new OnlineClientKey( "oa");
    public static OnlineClientKey onlineUser = new OnlineClientKey( "oc");
    public static OnlineClientKey getByAdminName = new OnlineClientKey( "adminName");
    public static OnlineClientKey getByUserName = new OnlineClientKey( "userName");
}
