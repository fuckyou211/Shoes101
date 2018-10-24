package com.shoes101.redis;

public class CatalogKey extends BasePrefix{
    public CatalogKey(String prefix) {
        super(prefix);
    }

    public CatalogKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static CatalogKey getLevelList = new CatalogKey(1800,"levelList");

    public static CatalogKey getHandleList = new CatalogKey(2000,"handleL");

    public static CatalogKey getCatalogInfo = new CatalogKey(1600,"gclInfo");
}
