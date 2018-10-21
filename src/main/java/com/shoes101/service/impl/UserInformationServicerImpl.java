package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.exception.GlobalException;
import com.shoes101.mapper.OrderdetailMapper;
import com.shoes101.mapper.ShoesorderMapper;
import com.shoes101.mapper.UserMapper;
import com.shoes101.mapper.UseraddressMapper;
import com.shoes101.pojo.Shoesorder;
import com.shoes101.pojo.User;
import com.shoes101.pojo.Useraddress;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.UserKey;
import com.shoes101.result.CodeMsg;
import com.shoes101.result.Result;
import com.shoes101.service.UserInformationServicer;
import com.shoes101.util.MD5Util;
import com.shoes101.vo.UserImformationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserInformationServicerImpl implements UserInformationServicer {


    private  final Logger logger= LoggerFactory.getLogger(UserInformationServicerImpl.class) ;

    @Autowired
    private ShoesorderMapper shoesorderMapper;

    @Autowired
    private OrderdetailMapper orderdetailMapper;

    @Autowired
    private UseraddressMapper useraddressMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisService redisService;

    @Override
    public Map<String,Object> UserMyAccount(HttpServletRequest request, User user) {
        Map<String,Object> information=new HashMap<>();
        information.put("order",myOrderMap(user));
        information.put("myInformation",myInformation(user));
        logger.info(JSONObject.toJSONString(information));
        return information;
    }

    public Map<String,Object> myInformation(User user){
        Map<String,Object> myInformation=new HashMap<>();
        myInformation.put("myorder",myOrder(user,0));
        myInformation.put("user",user);
        myInformation.put("userAdress",useraddressMapper.selectByPrimaryKey(user.getUserid()));
        return myInformation;
    }

    public Map<String,Object> myOrderMap(User user){
        Map<String,Object> orderMap=new HashMap<>();
        orderMap.put("nopayoder",myOrder(user,100));
        orderMap.put("readysend",myOrder(user,101));
        orderMap.put("alreadysend",myOrder(user,102));
        orderMap.put("alreadyget",myOrder(user,103));
        return orderMap;
    }


    public List<Map<String,Object>> myOrder(User user, Integer state)
    {
        List<Map<String,Object>> mapList=new ArrayList<>();
        List<Shoesorder> orderList=null;
        if(state.equals(0)){
            orderList=shoesorderMapper.getOrderByUserId(user.getUserid());
        }
        else if(state.equals(103)){
            orderList=shoesorderMapper.getshoesorderByorderidState1(user.getUserid(),state);
        }
        else {
            orderList=shoesorderMapper.getshoesorderByorderidState(user.getUserid(),state);
        }

        for(int i=0;i<orderList.size();i++)
        {
            Map<String,Object> shoesMap=new HashMap<>();
            Shoesorder shoesorder=orderList.get(i);
            shoesMap.put("shoesorder",shoesorder);
            shoesMap.put("orderDetail",orderdetailMapper.getOrderdetailByorderid(shoesorder.getOrderid()));
            mapList.add(shoesMap);
        }
        return mapList;
    }
    @Override
    public String UpdateInformation(HttpServletRequest request,User user,UserImformationVo userImformationVo){
        user.setUsername(userImformationVo.getUsername());
        user.setPhone(userImformationVo.getPhone());
        userMapper.updateByPrimaryKey(user);
        Useraddress useraddress=useraddressMapper.selectByPrimaryKey(user.getUserid());
        if(useraddress==null)
        {
            useraddress=new Useraddress();
            useraddress.setUserid(user.getUserid());
            useraddress.setAddress(userImformationVo.getAddress());
            useraddressMapper.insert(useraddress);
        }
        else
        {
            useraddress.setAddress(userImformationVo.getAddress());
            useraddressMapper.updateByPrimaryKey(useraddress);
        }
        logger.info(JSONObject.toJSONString(useraddress));
        return " 000";

    }
    @Override
    public Result<String> UpdatePassword(HttpServletRequest request, User user,String password,String setpassword)
    {
        if(user==null)
            throw new GlobalException(CodeMsg.ADMIN_NOT_LOGIN);
        String calcPass = MD5Util.formPassToDBPass(password, MD5Util.getSalt());
        if(!user.getPassword().equals(calcPass))
        {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        setpassword = MD5Util.formPassToDBPass(setpassword, MD5Util.getSalt());
        user.setPassword(setpassword);
        userMapper.updateByPrimaryKey(user);
        redisService.set(UserKey.getByPhone, ""+user.getPhone(), user);
        return Result.success("信息修改成功！");
    }

}
