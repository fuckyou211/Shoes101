package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.*;
import com.shoes101.pojo.*;
import com.shoes101.service.GoodsMService;
import com.shoes101.util.FileUtils;
import com.shoes101.util.RemoteUploadServiceUtil;
import com.shoes101.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;
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

    @Autowired
    private SplinkMapper splinkMapper;

    @Autowired
    RemoteUploadServiceUtil remoteUploadServiceUtil;

    private String remoteUrl ="http://123.207.109.158:9999/uploadfiles";


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
    @Transactional
    public String addShoesForm(Addshoes addshoes, HttpServletRequest request) {
        addshoes.setShoesid(SetShoesBean(addshoes));
        uploadShoespic(request,addshoes.getShoesid());
        List<List<Shoessku>> shoessku=SetShoessku(addshoes);
        uploadColorpic(request,shoessku,addshoes.getShoesid());
        SetSplink(addshoes);
        Spfilter(addshoes);
        return addshoes.getShoesid().toString();
    }

    @Override
    public String shoesShowAjax() {
        List<Shoes> list=shoesMapper.selectAll();
        List<Shoespic> list2=new ArrayList<>();
        List<Integer> list3=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            list3.add(shoesskuMapper.selectByshoesIdSum(list.get(i).getShoesid()));
            list2.add(shoespicMapper.selectByshoesid(list.get(i).getShoesid()));
        }
        Map<String,Object> map=new HashMap<>();
        map.put("GoodsList",list);
        map.put("picList",list2);
        map.put("number",list3);
        logger.info(JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
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

        List<String> res = remoteUploadServiceUtil.remoteUploadGetList(request,
                "shoespic", "http://123.207.109.158:9999/uploadService","shoesGoods/101");
        for(int i=0;i<res.size();i++)
        {
            Shoespic shoespic=new Shoespic();
            shoespic.setShoesid(shoesid);
            shoespic.setPicaddress(res.get(i));
            if(!res.get(i).equals(null))
            {
                shoespicMapper.insert(shoespic);
                logger.info(JSONObject.toJSONString(res)+"上传成功");
            }
        }


            return "00";
//        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("shoespic");
//        MultipartFile file = null;
//
//        for (int i = 0; i < fileList.size(); ++i) {
//            file = fileList.get(i);
//            logger.error(JSONObject.toJSONString(file));
//            if (file.isEmpty())
//            {
//                continue;
//            }
//            logger.info("i:"+i);
//            // 要上传的目标文件存放路径
//            String savePath = FileUtils.getWebContent() + File.separator + "src" + File.separator + "main" +
//                    File.separator+"resources"+ File.separator+"static"+ File.separator+"uploadfiles"+File.separator+"shoesGoods"+File.separator+shoesid;
//            //存放路径 创建
//            File dir=new File(savePath);
//            if(! dir.exists()){
//                dir.mkdirs();
//            }
//            String msg = "";
//            String name=file.getOriginalFilename();
//            String suffix = name.substring(name.lastIndexOf('.'));
//            String newFileName = new Date().getTime() + suffix;
//            String JDBCPath ="uploadfiles/shoesGoods/"+shoesid+"/"+newFileName;
//
//            if (FileUtils.upload(file, savePath, newFileName)) {
//                // 上传成功，给出页面提示
//                msg = "上传成功！";
//                Shoespic shoespic=new Shoespic();
//                shoespic.setShoesid(shoesid);
//                shoespic.setPicaddress(JDBCPath);
//                shoespicMapper.insert(shoespic);
//                logger.info(JSONObject.toJSONString(shoespic)+"上传成功");
//            } else {
//                msg = "上传失败！";
//                logger.info(shoesid+"第"+i+"张图上传失败");
//
//            }
//            // 显示图片
//            logger.info(msg);
//            logger.info(file.getOriginalFilename());
//        }
//        return "000";
    }

    @Override
    public String uploadColorpic(HttpServletRequest request,List<List<Shoessku>> shoessku,Integer shoesid){

        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("shoescolorpic");
        MultipartFile file = null;

        for (int i = 0; i < fileList.size(); ++i) {
            file = fileList.get(i);
            logger.info("i:"+i);
            // 要上传的目标文件存放路径
            if (file.isEmpty())
            {
                continue;
            }

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

    @Override
    public String editQuantitAjax(Integer shoesid) {

        Map<String,Object> map=new HashMap<>();
        Integer colorId=propertyMapper.selectColorPropertyvalue(color);
        Integer sizeId=propertyMapper.selectColorPropertyvalue(size);
        logger.info("shoesid:"+shoesid+"colorId:"+colorId+" size:"+sizeId);
        List<Splink> listColor=splinkMapper.selectByShoesIdProperty(shoesid,colorId);
        List<Splink> listSize=splinkMapper.selectByShoesIdProperty(shoesid,sizeId);

        map.put("listColor",propertyvalueMapper.selectByShoesIdProperty(shoesid,colorId));
        map.put("listSize",propertyvalueMapper.selectByShoesIdProperty(shoesid,sizeId));
        List<List<Shoessku>> listQuantit=new ArrayList<>();

        for(int i=0;i<listSize.size();i++)
        {
            List<Shoessku> list=new ArrayList<>();
            for(int j=0;j<listColor.size();j++)
            {
               String skuproperty= "{"+colorId+":"+listColor.get(j).getPropertyvalueid()+","+sizeId+":"+listSize.get(i).getPropertyvalueid()+"}";
               list.add(shoesskuMapper.selectskuproperty(shoesid,skuproperty));
            }
            listQuantit.add(list);
        }
        map.put("listQuantit",listQuantit);
        logger.info(JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
    }

    @Override
    @Transactional
    public String setQuantitAjax(List<Integer> skuid,List<Integer> quantity) {
        for(int i=0;i<skuid.size();i++)
        {
            logger.info("skuid:"+skuid.get(i)+"quantity:"+quantity.get(i));
            shoesskuMapper.selectquantity(skuid.get(i),quantity.get(i));
        }
        return "";
    }

    @Override
    @Transactional
    public String setisdropoffAjax(Integer shoesid,String status) {

        shoesMapper.selectupdateisdropoff(shoesid,status);
        return "";
    }

    //根据商品id获取商品详情
    //10.9更新 前台后台区分1 2 1前台
    public String todetail(int shoesid)
    {

        //获取商品名、库存、价格、描述
        FDetailsVo fDetailsVo = shoesMapper.getDetails(shoesid);

        //10.10 更新 引入PropAndPropvVo
        List<PropAndPropvVo> propAndPropvVos = new ArrayList<>();

        //10.10更新 获取鞋子的所有大图
        List<String> shoespics = shoesMapper.getAllPic(shoesid);

        //10.8 更新 引入 ColAndPicVo
        List<ColAndPicVo> colAndPicVos = new ArrayList<>();

        //10.10更新 引入 分类
        List<String> catalognameList = new ArrayList<>();

//        System.out.println("A");
        //根据商品Id查找属性关系表
        List<Splink> sp = shoesMapper.getPidById(shoesid);

//        System.out.println("B");
        //根据尺码属性（3）搜索出所有的鞋码的属性名编号存好属性值
        List<String> colorlist = new ArrayList<>();

        //10.8 废弃SizeList 并入SizeAndIdVo
        List<SizeAndIdVo> sizeAndIdVos = new ArrayList<>();
//        List<String> sizelist = new ArrayList<>();
//        //存取商品的图片 10.8更新 废除该List 并入ColAndPicVo
//        List<String> colorpic = new ArrayList<>();

        String colorstr = "2:";//模糊查询 匹配颜色

        //10.10 获取传入鞋的所有分类
        int catalogid = shoesMapper.getShoesCatalog(shoesid);
        CatalognameAndParentVo vo = shoesMapper.getCatalognameAndFather(catalogid);
        catalognameList.add(vo.getCatalogname());
        int confirm = vo.getParentid();
        while(confirm != 0)
        {
            CatalognameAndParentVo whileVo = shoesMapper.getCatalognameAndFather(confirm);
            catalognameList.add(whileVo.getCatalogname());
            System.out.println(whileVo.getCatalogname());
            confirm = whileVo.getParentid();
        }
        //逆序分类
        Collections.reverse(catalognameList);

        for(int i = 0;i < sp.size();i++)
        {
//            System.out.println("pid" + sp.get(i).getPropertyid());
//            System.out.println("pvid" + sp.get(i).getPropertyvalueid());
            //遍历 如果这是尺码(3)
            if(sp.get(i).getPropertyid().equals(3))
            {
                SizeAndIdVo demo = new SizeAndIdVo();
                demo.setSizeid(sp.get(i).getPropertyvalueid());
                demo.setSize(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
                sizeAndIdVos.add(demo);
//                sizelist.add(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
//                System.out.println("C");
            }
            //如果是颜色(2)
            else if(sp.get(i).getPropertyid().equals(2))
            {
                colorlist.add(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
//                System.out.println("C");
            }
            //如果是其他属性 获取其名字和属性
            else{
                PropAndPropvVo demo = new PropAndPropvVo();
                demo.setProperty(propertyMapper.getPropname(sp.get(i).getPropertyid()));
                demo.setPropertyvalue(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
//                System.out.println("property:" + demo.getProperty());
//                System.out.println("propertyvalue" + demo.getPropertyvalue());
                propAndPropvVos.add(demo);
            }
        }
        //根据商品id和颜色属性获取商品的skuid 用于下一步根据skuid获取到第一个商品图片 商品详情页的颜色小图片显示
        //10.8更新 新增颜色与图片VO 用于放置颜色图片与shoes-detail中指针放置图片显示颜色名
        //遍历 一个个颜色去找
        for(int i = 0;i < colorlist.size();i++)
        {
            ColAndPicVo demo = new ColAndPicVo();
            //'%3:#{color}%'
            //10.4更新 根据搜索到的字符串获取属性值id 再进行查找skuid
            int pvid = propertyvalueMapper.getIdByValue(colorlist.get(i).toString());
            String pvidtostr = String.valueOf(pvid);
            String str = "'%" + colorstr + pvidtostr + "%'";
            System.out.println(str);
            System.out.println(shoesid);
            //获取skuid
            int skuid = shoesMapper.getTheSkuid(shoesid,str);
//            System.out.println("D");
            demo.setColorpic(shoesMapper.getColorPicForDetail(skuid));
            demo.setColor(colorlist.get(i).toString());
            demo.setColorid(pvid);
            colAndPicVos.add(demo);
//            colorpic.add(shoesMapper.getColorPicForDetail(skuid));
//            System.out.println("E");
        }

        //10.9 商品描述由String 转 JSON 解决转义缺少反斜杠问题
        String jsonDetail = JSONObject.toJSONString(shoesMapper.getShoesDetail(shoesid));
        fDetailsVo.setShoesdetails(shoesMapper.getShoesDetail(shoesid));

        //10.10 获取所有库存 计算总库存
        int totalQty = shoesMapper.calTotalQty(shoesid);



//        System.out.println("F");
        Map<String,Object> map = new HashMap<>();
        map.put("totalQty",totalQty);
        map.put("catalog",catalognameList);
        map.put("bigpics",shoespics);
        map.put("propertys",propAndPropvVos);
        //map.put("shoesdetails",jsonDetail);
        map.put("details",fDetailsVo);
        map.put("sizelist",sizeAndIdVos);
        //map.put("colorlist",colorlist);
        map.put("colorpicandcolor",colAndPicVos);
        logger.info(JSONObject.toJSONString(map));
//        System.out.println("G");
        return JSONObject.toJSONString(map);

    }

}
