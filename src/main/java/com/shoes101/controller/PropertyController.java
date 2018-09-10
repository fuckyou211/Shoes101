package com.shoes101.controller;

import com.shoes101.pojo.Property;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 验证是否有相同属性
     */
    @RequestMapping("/checkExist")
    @ResponseBody
    public int checkExist(@RequestParam("propertyvalue") String propertyvalue)
    {
        return propertyService.checkExist(propertyvalue);
    }

    /**
     * 新增属性  会验证是否与之前的相同 将返回的列表装入Map返回
     */
    @RequestMapping("/addProp")
    @ResponseBody
    public List<Property> addProp(@RequestParam("propertyname") String propertyname, @RequestParam("propertyvalue") String propertyvalue)
    {
        return propertyService.addProp(propertyname,propertyvalue);
    }
//    备用方法
//    @RequestMapping("/addProp")
//    public String addProp(@RequestParam("propertyname") String propertyname, @RequestParam("propertyvalue") String propertyvalue, Map<String,Object> map)
//    {
//        List<Property> list = propertyService.addProp(propertyname,propertyvalue;
//        map.put("list",list);
//        return "back/manager_property";
//    }

    /**
     * 修改属性
     */
    @RequestMapping("/updateProp")
    @ResponseBody
    public List<Property> updateProp(@RequestParam("propertyvalue") String propertyvalue,@RequestParam("propertyid") int propertyid)
    {
        return propertyService.updateProp(propertyvalue,propertyid);
    }

    /**
     * 删除属性 两张表一起删
     */
    @RequestMapping("/deleteProp")
    @ResponseBody
    public List<Property> deleteProp(@RequestParam("propertyid") int propertyid)
    {
        return propertyService.deleteProp(propertyid);
    }

    /**
     * 获取详细属性值 传入propertyid
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public Propertyvalue getDetail(@RequestParam int propertyid)
    {
        return propertyService.getProperty(propertyid);
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
