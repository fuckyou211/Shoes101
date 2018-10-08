package com.shoes101.controller.BackStage;

import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.User;
import com.shoes101.service.MUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.HashMap;
import java.util.List;

/**
 * 后台用户管理控制器
 */

@Controller
@RequestMapping("/muser")
public class MUserController {

    @Autowired
    private MUserService mUserService;

    /**
     * 跳转到用户管理界面
     * @return
     */
    @RequestMapping("/tomuser")
    public String tomuser(HashMap<String,Object> map)
    {
        List<User> list0 = mUserService.getAllUser();
        String list = JSON.toJSONString(list0);
        System.out.println(list.isEmpty());
        map.put("list",list);
        return "back/manager_user";
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/colduser")
    @ResponseBody
    public List<User> coldUser(@RequestParam("userid") int userid)
    {
        return mUserService.getCold(userid);
    }


}
