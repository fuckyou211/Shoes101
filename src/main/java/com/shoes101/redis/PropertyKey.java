package com.shoes101.redis;

public class PropertyKey extends BasePrefix{

    public PropertyKey(String prefix) {
        super(prefix);
    }

    public PropertyKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static PropertyKey getPropertyList = new PropertyKey(1800,"propList");
}
