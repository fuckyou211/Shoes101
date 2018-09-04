package com.shoes101.controller;

import com.shoes101.pojo.User;
import com.shoes101.redis.RedisService;
import com.shoes101.result.Result;
import com.shoes101.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

        @Autowired
        private RedisService redisService;

        @Autowired
        private UserService userService;

//        @RequestMapping(value="/get", produces = {"application/json;charset=UTF-8"})
//        @ResponseBody
//        public User getAllUser()
//        {
//
//            return userService.getUser();
//        }

        @RequestMapping("/redis2")
        @ResponseBody
        public Result<Long> redisGet()
        {
            Long v1 = redisService.get("key1",Long.class);
            return Result.success(v1);
        }



        @RequestMapping("/redis")
        @ResponseBody
        public Result<String> redisSet()
        {

            boolean ret = redisService.set("key2","hi");
            String str = redisService.get("key2",String.class);
            return Result.success(str);
//            Jedis jedis = new Jedis("127.0.0.1",6379);
//            jedis.set("message","123456");
//            String value=jedis.get("message");
//            System.out.println(value);
//            jedis.close();
//            return value;
        }

//        @ResponseBody
//        @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
//        public int addUser(User user){
//            user.setPassword("123");
//            user.setPhone("111");
//            //user.setUserId(1);
//            user.setUserName("sam");
//            return userService.addUser(user);
//         }
}

