package com.shoes101.redis;

public class RushKey extends BasePrefix {
    public RushKey(String prefix) {
        super(prefix);
    }

    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    /**
     *
     * @param expireSeconds  有效时间
     * @param prefix  key前缀
     */
    public RushKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static RushKey token = new RushKey(TOKEN_EXPIRE, "tk");
    public static RushKey rushgood = new RushKey(TOKEN_EXPIRE, "rushshoesid");
    public static RushKey rushsku = new RushKey(TOKEN_EXPIRE, "rushsku");
    public static RushKey rushLimit = new RushKey(TOKEN_EXPIRE, "rushLimit");
    public static RushKey userLimit = new RushKey(TOKEN_EXPIRE, "userLimit");
    public static RushKey orderState = new RushKey(TOKEN_EXPIRE, "orderState");
    public static RushKey orderId = new RushKey(TOKEN_EXPIRE, "orderId");


}