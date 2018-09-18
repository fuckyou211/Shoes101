package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.PropertyMapper;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.mapper.ShoescatalogMapper;
import com.shoes101.service.GoodsMService;
import com.shoes101.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;


@Service
public class GoodsMServiceImpl implements GoodsMService {

    @Value("${color}")
    private String color;

    @Value("${size}")
    private String size;

    private  final Logger logger= LoggerFactory.getLogger(GoodsMServiceImpl.class) ;

    @Autowired
    private ShoescatalogMapper shoescatalogMapper;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyvalueMapper propertyvalueMapper;

    @Override
    public String addShoesInformationAjax() {
        Map<String,Object> list=new HashMap<String,Object>();
        list.put("shoesid",new Date().getTime());
        list.put("catalog",shoescatalogMapper.findCatalogByPid(0));
        list.put("property",propertyMapper.selectAll());
        list.put("colorProperty",propertyvalueMapper.selectColorPropertyvalue(color));
        list.put("sizeProperty",propertyvalueMapper.selectColorPropertyvalue(size));

        return JSONObject.toJSONString(list);
    }

    @Override
    public String shoesCatalogAjax(Integer parentId) {
        return JSONObject.toJSONString(shoescatalogMapper.findCatalogByPid(parentId));
    }

    @Override
    public String upload(HttpServletRequest request){

        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;

        for (int i = 0; i < fileList.size(); ++i) {
            file = fileList.get(i);
            logger.info("i:"+i);
            // 要上传的目标文件存放路径

            String savePath = FileUtils.getWebContent() + File.separator + "src" + File.separator + "main" +
                    File.separator+"resources"+ File.separator+"static"+ File.separator+"uploadfiles";
            String localPath = "D:/Information/picture";
            // 上传成功或者失败的提示
            String msg = "";
            String name=file.getOriginalFilename();
            String suffix = name.substring(name.lastIndexOf('.'));
            String newFileName = new Date().getTime() + suffix;

            if (FileUtils.upload(file, savePath, newFileName)) {
                // 上传成功，给出页面提示
                msg = "上传成功！";
            } else {
                msg = "上传失败！";

            }

            // 显示图片
            logger.info(msg);
            logger.info(file.getOriginalFilename());

        }
        return "0000";
    }

    @Override
    public String shoesPropertyAjax(Integer propertyId) {
        return JSONObject.toJSONString(propertyvalueMapper.selectPropertyvalueBypropertyId(propertyId));
    }


}
