package com.shoes101.util;

import org.apache.commons.codec.digest.DigestUtils;

//MD5加密 2次MD5
public class MD5Util {
    public static String md5(String src)
    {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassFormPass(String inputPass)
    {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //存入数据库的Salt 不可随机
    public static String formPassToDBPass(String formPass,String salt)
    {
        String str = "" + salt.charAt(0) + salt.charAt(2)  + formPass + salt.charAt(5) + salt.charAt(4);;
        return md5(str);
    }

    public static void main(String[] args)
    {
        System.out.println(inputPassFormPass("123456"));
        System.out.println(formPassToDBPass(inputPassFormPass("123456"),"1a2b3c4d"));
    }

}
