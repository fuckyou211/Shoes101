package com.shoes101.controller;

import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.Property;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.service.PropertyService;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性管理控制器
 */
@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * 跳转到属性管理 获取全部属性
     */
    @RequestMapping("/toproperty")
    public String toproperty(HashMap<String,Object> map)
    {
        System.out.println("1111111");
        List<Property> list1 = propertyService.getAllProperty();
        System.out.println(list1.isEmpty());
        String list= JSON.toJSONString(list1);
        map.put("list",list);

        System.out.println(list);
        return "back/manager_property";
    }

    /**
     * 验证是否有相同属性值
     */
    @RequestMapping("/checkExist")
    @ResponseBody
    public int checkExist(@RequestParam("propertyvalue") String propertyvalue)
    {
        return propertyService.checkExist(propertyvalue);
    }

    /**
     * 验证是否有相同属性
     */
    @RequestMapping("/checkExistP")
    @ResponseBody
    public int checkExistP(@RequestParam("propertyname") String propertyname)
    {
        return propertyService.checkExistP(propertyname);
    }

    /**
     * 新增属性  会验证是否与之前的相同 将返回的列表装入Map返回
     */
    @RequestMapping("/addProp")
    @ResponseBody
    public List<Property> addProp1(@RequestParam("propertyname") String propertyname)
    {
        return propertyService.addProp1(propertyname);
    }
//    public List<Property> addProp(@RequestParam("propertyname") String propertyname, @RequestParam("propertyvalue") String propertyvalue)
//    {
//        return propertyService.addProp(propertyname,propertyvalue);
//    }
//    备用方法
//    @RequestMapping("/addProp")
//    public String addProp(@RequestParam("propertyname") String propertyname, @RequestParam("propertyvalue") String propertyvalue, Map<String,Object> map)
//    {
//        List<Property> list = propertyService.addProp(propertyname,propertyvalue;
//        map.put("list",list);
//        return "back/manager_property";
//    }

//    /**
//     * 根据id获取属性名
//     */
//    @RequestMapping("/getPropname")
//    @ResponseBody
//    public String getPropname(@RequestParam("propertyid") int propertyid)
//    {
//        return propertyService.getPropname(propertyid);
//    }


    /**
     * 新增属性值
     */
    @RequestMapping("/addpropv")
    @ResponseBody
    public List<Propertyvalue> addPropV(@RequestParam("propertyid") int propertyid, @RequestParam("propertyvalue") String propertyvalue)
    {
       return propertyService.addPropv(propertyid,propertyvalue);
   }


    /**
     * 修改属性
     */
    @RequestMapping("/updateP")
    @ResponseBody
    public List<Property> updateP(@RequestParam("propertyid") int propertyid,@RequestParam("propertyname") String propertyname)
    {
        return propertyService.updateP(propertyid,propertyname);
    }

    /**
     * 修改属性详细值
     */
    @RequestMapping("/updateProp")
    @ResponseBody
    public List<Propertyvalue> updateProp(@RequestParam("propertyvalue") String propertyvalue,@RequestParam("propertyid") int propertyid)
    {
        return propertyService.updateProp(propertyvalue,propertyid);
    }

    /**
     * 删除属性 两张表一起删
     */
    @RequestMapping("/deletePropAndValue")
    @ResponseBody
    public List<Property> deleteProp(@RequestParam("propertyid") int propertyid)
    {
        return deleteProp(propertyid);
    }

    /**
     * 删除属性值 只动Propertyvalue
     */
    @RequestMapping("/deletePropV")
    @ResponseBody
    public List<Propertyvalue> deletePropV(@RequestParam("propertyvalueid") int propertyvalueid)
    {
        return propertyService.deletePropV(propertyvalueid);
    }

    /**
     * 获取详细属性值 传入propertyid
     */
    @RequestMapping("/getDetail")
    public String getDetail(@RequestParam("propertyid") int propertyid,HashMap<String,Object> map)
    {
        List<Propertyvalue> pv = propertyService.getProperty(propertyid);
        String list= JSON.toJSONString(pv);
        String propname = propertyService.getPropname(propertyid);
        Property property = propertyService.getPropertyAndName(propertyid);
        String Aproperty= JSON.toJSONString(property);
        map.put("pv",list);
        map.put("property",Aproperty);
        return "back/manager_propval";
    }



    /**
     * 搜索属性
     */
//    public static void main(String[] args)
//    {
//        PropertyController a = new PropertyController();
//        int result = a.addProp("颜色","黑色");
//        System.out.println(result);
//    }

}
