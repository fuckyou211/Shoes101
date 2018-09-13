package com.shoes101.controller;

import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.Property;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.service.CatalogService;
import com.shoes101.service.PropertyService;
import com.shoes101.service.ShoesService;
import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 商品控制器（后台）
 */


@Controller
@RequestMapping("/shoes")
public class ShoesController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private ShoesService shoesService;
    /**
     * 跳转到商品管理
     */

    @RequestMapping("/toshoes")
    public String toshoes(HashMap<String,Object> map)
    {
        System.out.println("1111111");
        List list1 = shoesService.getAllShoes();
        System.out.println(list1.isEmpty());
        String list= JSON.toJSONString(list1);
        map.put("list",list);

        System.out.println(list);

        return "back/manager_shoes";
    }

    /**
     * 获取所有属性
     */
    @RequestMapping("/getshoesprop")
    @ResponseBody
    public HashMap getShoesProp(HashMap<String,Object> map)
    {
        //方案1 直接获取属性内容
        //方案2 根据属性id一个个对应查找出来属性详细值
        List<Property> list1 = propertyService.getAllProperty();
        List<Propertyvalue> list2 = propertyService.getAllPropertyValue();
        System.out.println(list1.isEmpty());
        String list= JSON.toJSONString(list1);
        String list3= JSON.toJSONString(list2);
        map.put("listp",list);
        map.put("listpv",list3);

        System.out.println(list);
        System.out.println(list3);
        return map;
    }

    /**
     * 获取所有分类
     */
//    @RequestMapping("/getshoescata")
//    @ResponseBody
//    public HashMap getShoesCata(HashMap<String,Object> map)
//    {
//
//        return map;
//    }

    /**
     * 上传图片
     */
    /**
     *
     * @param file 要上传的文件
     * @return
     */
//    @RequestMapping("fileUpload")
//    public String upload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){
//
//        // 要上传的目标文件存放路径
//        String localPath = "E:/Develop/Files/Photos";
//        // 上传成功或者失败的提示
//        String msg = "";
//
//        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
//            // 上传成功，给出页面提示
//            msg = "上传成功！";
//        }else {
//            msg = "上传失败！";
//
//        }
//
//        // 显示图片
//        map.put("msg", msg);
//        map.put("fileName", file.getOriginalFilename());
//
//        return "forward:/test";
//    }
//
//    /**
//     * 显示单张图片
//     * @return
//     */
//    @RequestMapping("show")
//    public ResponseEntity showPhotos(String fileName){
//
//        try {
//            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
//            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }


    /**
     * 增加商品
     */

}
