package com.shoes101.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//redis Service

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;



    public <T> T get(String key,Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            jedisPool.returnBrokenResource(jedis);

        }
    }

    public <T> boolean set(String key,T value)
    {
        System.out.println("111111111");
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0)
            {
                return false;
            }
            jedis.set(key,str);
            return true;
        }catch(RuntimeException e)
        {
            if(jedis != null)
                jedisPool.returnBrokenResource(jedis);

        }finally{
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    private <T> String beanToString(T value)
    {
        if(value == null)
        {
            return null;
        }
        Class<?> clazz = value.getClass();//参数校验 有可能是各种类型的数据
        if(clazz == int.class|| clazz == Integer.class){
            return ""+value;
        }else if(clazz == String.class)
        {
            return(String)value;
        }else if(clazz == long.class || clazz == Long.class)
        {
            return ""+value;
        }
        return JSON.toJSONString(value);
    }

    private<T> T stringToBean(String str,Class<T> clazz)//全部的转化过程是上个函数的反过程
    {
        if(str == null|| str.length() <= 0 || clazz == null)
        {
            return null;
        }
        if(clazz == int.class||clazz==Integer.class)
        {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class)
        {
            return (T)Long.valueOf(str);
        }else{
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private void returnToPool(Jedis jedis)
    {
        if(jedis != null)
        {
            jedis.close();
        }
    }



}
