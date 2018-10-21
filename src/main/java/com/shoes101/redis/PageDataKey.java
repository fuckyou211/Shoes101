package com.shoes101.redis;

public class PageDataKey extends BasePrefix {
    public PageDataKey(String prefix) {
        super(prefix);
    }

    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    /**
     *
     * @param expireSeconds  有效时间
     * @param prefix  key前缀
     */
    public PageDataKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static PageDataKey orderData = new PageDataKey(60, "orderData");


}
