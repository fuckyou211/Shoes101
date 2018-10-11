package com.shoes101.redis;

public class SmsKey extends BasePrefix{
    public SmsKey(String prefix) {
        super(prefix);
    }
    /**
     *
     * @param expireSeconds  有效时间
     * @param prefix  key前缀
     */
    public SmsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SmsKey smsLimitKey(int expireSeconds, String prefix){
        return new SmsKey(expireSeconds,prefix);
    }
}
