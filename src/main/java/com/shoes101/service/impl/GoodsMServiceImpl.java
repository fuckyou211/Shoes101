package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.*;
import com.shoes101.pojo.*;
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
import java.text.SimpleDateFormat;
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

    @Autowired
    private ShoesMapper shoesMapper;

    @Autowired
    private ShoesskuMapper shoesskuMapper;

    @Autowired
    private SplinkMapper SplinkMapper;

    @Autowired
    private SpfilterMapper spfilterMapper;

    @Autowired
    private ShoespicMapper shoespicMapper;

    @Autowired
    private ColorpicMapper colorpicMapper;

    @Override
    public String addShoesInformationAjax() {
        Map<String,Object> list=new HashMap<String,Object>();
        list.put("shoesid","000");
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
    public String shoesPropertyAjax(Integer propertyId) {
        return JSONObject.toJSONString(propertyvalueMapper.selectPropertyvalueBypropertyId(propertyId));
    }

    @Override
    public String addShoesForm(Addshoes addshoes, HttpServletRequest request) {
        addshoes.setShoesid(SetShoesBean(addshoes));
        uploadShoespic(request,addshoes.getShoesid());
        List<List<Shoessku>> shoessku=SetShoessku(addshoes);
        uploadColorpic(request,shoessku,addshoes.getShoesid());
        SetSplink(addshoes);
        Spfilter(addshoes);
        return null;
    }

    public Integer SetShoesBean(Addshoes addshoes){
        Shoes shoes=new Shoes();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=new Long(new Date().getTime());
        String d = format.format(time);
        shoes.setAdddate(d);
        shoes.setShoesname(addshoes.getShoesname());
        shoes.setCatalogid(addshoes.getCatalogid());
        shoes.setSales(0);
        shoes.setIsdropoff(addshoes.getIsdropoff());
        shoes.setShoesdetails(addshoes.getShoesdetails());
        shoes.setLabel(addshoes.getLabel());
        shoesMapper.insert(shoes);
        logger.info(JSONObject.toJSONString(shoes));
        return shoes.getShoesid();
    }

    public List<List<Shoessku>> SetShoessku(Addshoes addshoes){
        List<List<Shoessku>> list=new ArrayList<>();
        Integer colorId=propertyMapper.selectColorPropertyvalue(color);
        Integer sizeId=propertyMapper.selectColorPropertyvalue(size);
        for (int i=0;i<addshoes.getShoescolor().size();i++){
            List<Shoessku> list1=new ArrayList<>();
            for(int j=0;j<addshoes.getShoessize().size();j++){
                Map<Integer,Integer> map=new HashMap<>();
                map.put(colorId,addshoes.getShoescolor().get(i));
                map.put(sizeId,addshoes.getShoessize().get(j));
                Shoessku shoessku=new Shoessku();
                shoessku.setShoesid(addshoes.getShoesid());
                shoessku.setPrice(addshoes.getPrice());
                shoessku.setTicketprice(addshoes.getPrice());
                shoessku.setQuantity(0);
                shoessku.setSkuproperty(JSONObject.toJSONString(map));
                shoessku.setSkusales(0);
                shoesskuMapper.insert(shoessku);
                logger.info(JSONObject.toJSONString(shoessku));
                list1.add(shoessku);
            }
            list.add(list1);
            logger.info(JSONObject.toJSONString(list));
        }
        return list;
    }

    public  void  SetSplink(Addshoes addshoes){
        Integer colorId=propertyMapper.selectColorPropertyvalue(color);
        Integer sizeId=propertyMapper.selectColorPropertyvalue(size);
        for (int i=0;i<addshoes.getShoessize().size();i++)
        {
            Splink splink=new Splink();
            splink.setShoesid(addshoes.getShoesid());
            splink.setPropertyid(sizeId);
            splink.setPropertyvalueid(addshoes.getShoessize().get(i));
            SplinkMapper.insert(splink);
        }

        for (int i=0;i<addshoes.getShoescolor().size();i++){
            Splink splink=new Splink();
            splink.setShoesid(addshoes.getShoesid());
            splink.setPropertyid(colorId);
            splink.setPropertyvalueid(addshoes.getShoescolor().get(i));
            SplinkMapper.insert(splink);
        }

        for (int i=0;i<addshoes.getProperty().size();i++){
            Splink splink=new Splink();
            splink.setShoesid(addshoes.getShoesid());
            splink.setPropertyid(addshoes.getProperty().get(i));
            splink.setPropertyvalueid(addshoes.getPropertyvalue().get(i));
            SplinkMapper.insert(splink);
        }
        logger.info(JSONObject.toJSONString("属性添加成功"));
    }

    public void Spfilter(Addshoes addshoes){
        Spfilter spfilter=new Spfilter();
        List<Integer> list=new ArrayList<>();
        list.addAll(addshoes.getShoescolor());
        list.addAll(addshoes.getShoessize());
        list.addAll(addshoes.getPropertyvalue());
        spfilter.setShoesid(addshoes.getShoesid());
        spfilter.setPropertyvalueid(JSONObject.toJSONString(list));
        spfilterMapper.insert(spfilter);
        logger.info(JSONObject.toJSONString(spfilter));
    }
    @Override
    public String uploadShoespic(HttpServletRequest request,Integer shoesid){

        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("shoespic");
        MultipartFile file = null;

        for (int i = 0; i < fileList.size(); ++i) {
            file = fileList.get(i);
            logger.info("i:"+i);
            // 要上传的目标文件存放路径
            String savePath = FileUtils.getWebContent() + File.separator + "src" + File.separator + "main" +
                    File.separator+"resources"+ File.separator+"static"+ File.separator+"uploadfiles"+File.separator+"shoesGoods"+File.separator+shoesid;
            //存放路径 创建
            File dir=new File(savePath);
            if(! dir.exists()){
                dir.mkdirs();
            }
            String msg = "";
            String name=file.getOriginalFilename();
            String suffix = name.substring(name.lastIndexOf('.'));
            String newFileName = new Date().getTime() + suffix;
            String JDBCPath ="uploadfiles/shoesGoods/"+shoesid+"/"+newFileName;

            if (FileUtils.upload(file, savePath, newFileName)) {
                // 上传成功，给出页面提示
                msg = "上传成功！";
                Shoespic shoespic=new Shoespic();
                shoespic.setShoesid(shoesid);
                shoespic.setPicaddress(JDBCPath);
                shoespicMapper.insert(shoespic);
                logger.info(JSONObject.toJSONString(shoespic)+"上传成功");
            } else {
                msg = "上传失败！";
                logger.info(shoesid+"第"+i+"张图上传失败");

            }
            // 显示图片
            logger.info(msg);
            logger.info(file.getOriginalFilename());
        }
        return "000";
    }

    @Override
    public String uploadColorpic(HttpServletRequest request,List<List<Shoessku>> shoessku,Integer shoesid){

        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("shoescolorpic");
        MultipartFile file = null;

        for (int i = 0; i < fileList.size(); ++i) {
            file = fileList.get(i);
            logger.info("i:"+i);
            // 要上传的目标文件存放路径

            String savePath = FileUtils.getWebContent() + File.separator + "src" + File.separator + "main" +
                    File.separator+"resources"+ File.separator+"static"+ File.separator+"uploadfiles"+File.separator+"shoesGoods"+File.separator+shoesid;
            //存放路径 创建
            File dir=new File(savePath);
            if(! dir.exists()){
                dir.mkdirs();
            }
            String msg = "";
            String name=file.getOriginalFilename();
            String suffix = name.substring(name.lastIndexOf('.'));
            String newFileName = new Date().getTime() + suffix;
            String JDBCPath ="uploadfiles/shoesGoods/"+shoesid+"/"+newFileName;
            if (FileUtils.upload(file, savePath, newFileName)) {
                // 上传成功，给出页面提示
                msg = "上传成功！";
                for(int j=0;j<shoessku.get(i).size();j++){
                    Colorpic colorpic=new Colorpic();
                    colorpic.setColorpicaddredd(JDBCPath);
                    colorpic.setSkuid(shoessku.get(i).get(j).getSkuid());
                    colorpicMapper.insert(colorpic);
                }
                logger.info("图片颜色图 第"+i+"张图上传成功");


            } else {
                msg = "上传失败！";
                logger.info("图片颜色图 第"+i+"张图上传失败");

            }

        }
        return "000";
    }

}
