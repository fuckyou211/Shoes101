package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shoes101.controller.BackStage.GoodsMController;
import com.shoes101.pojo.Property;
import com.shoes101.redis.FGoodsKey;
import com.shoes101.redis.RedisService;
import com.shoes101.result.Result;
import com.shoes101.service.*;
import com.shoes101.util.ListHandleUtils;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.PropertyValueVo;
import com.shoes101.vo.SkuIdAndQtyVo;
import com.shoes101.vo.pageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 前台商品展示
 */
@Controller
@RequestMapping("/goodsf")
public class GoodsFController {

    private  final Logger logger= LoggerFactory.getLogger(GoodsMController.class) ;

    @Autowired
    private GoodsFService goodsFService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ShoesHeaderService shoesHeaderService;
    @Autowired
    private PageSevice pageSevice;
    @Autowired
    private SearchService searchService;
    @Autowired
    private PropertyFilterServie propertyFilterServie;
    @Autowired
    private PropertyService propertyService;

    /**
     * 获取商品列表
     * 根据商品id获取商品图片、价格、名称、并且获取所有库存不为0
     */
    @RequestMapping("/tolist")
    @ResponseBody
    public Result<String> tolist()
    {
//        System.out.println("11");
        //取缓存
        String existList = redisService.get(FGoodsKey.getGoodsList,"good_list",String.class);
//        System.out.println("111");
//        System.out.println(existList);
//        System.out.println("111");
        if(existList != null)
        {
            System.out.println("Using Redis to get Goodslist");
            return JSONObject.toJavaObject(JSONObject.parseObject(existList),Result.class);
        }
        //若缓存不存在
        Result result=Result.success(goodsFService.tolist());
        redisService.set(FGoodsKey.getGoodsList,"good_list",JSONObject.toJSONString(result));
        return result;
    }


    /**
     * 获取商品详情 传入商品Id
     *
     */
    @RequestMapping("/todetail/{shoesid}")
    @ResponseBody
    public Result<String> todetail(@PathVariable("shoesid") int shoesid)
    {
        System.out.println(""+shoesid);
        //取缓存
        String existDetail = redisService.get(FGoodsKey.getGoodsDetail,""+shoesid,String.class);
        System.out.println(existDetail);
        if(existDetail != null)
        {
            System.out.println("Using Redis to get detail");
            return JSONObject.toJavaObject(JSONObject.parseObject(existDetail),Result.class);
        }
        //若缓存不存在
        Result result=Result.success(goodsFService.todetail(shoesid));
        redisService.set(FGoodsKey.getGoodsDetail,""+shoesid,JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 根据id 颜色 尺码 返回相应库存以及相应skuid供根据skuid下订单
     */
    @RequestMapping("/getQtyAjax")
    @ResponseBody
    public SkuIdAndQtyVo getQuantity(@RequestParam("shoesid") int shoesid,@RequestParam(value="colorid", required = false) String colorid,@RequestParam(value = "sizeid", required = false) String sizeid)
    {
        //取缓存
        SkuIdAndQtyVo existQtyAndId = redisService.get(FGoodsKey.getQtyAndSkuId,"shoesid" + shoesid + "colorid" + colorid + "sizeid" + sizeid,SkuIdAndQtyVo.class);
        if(existQtyAndId != null)
        {
            System.out.println("Using Redis to get skuid and quantity");
            return existQtyAndId;
        }
        logger.info("Qty:" + goodsFService.getQty(shoesid,colorid,sizeid));
        SkuIdAndQtyVo saq = goodsFService.getQty2(shoesid,colorid,sizeid);
        redisService.set(FGoodsKey.getQtyAndSkuId,"shoesid" + shoesid + "colorid" + colorid + "sizeid" + sizeid,goodsFService.getQty2(shoesid,colorid,sizeid));
        return saq;
    }

    //分页（针对的是当前展示的商品为某分类下的，或者某属性值下的）
    @RequestMapping("/getShoesListByPage")
    @ResponseBody
    public Result getShoesListByPage(Integer pageCode,Integer size,Integer catalogId, Integer propertyValueId,Integer filterFlag,String filterList){
        List<FGoodsVo> list = new ArrayList<FGoodsVo>();
        pageBean pb = new pageBean();
        if(filterFlag==0){
            if(catalogId!=null){
                //获得此catalogId下的所有鞋
                list =  shoesHeaderService.listUnderCatalog(catalogId);
                List<FGoodsVo> newList = ListHandleUtils.getPartOfList(list,pageCode,size);
                pb = pageSevice.setTopageBean(pageCode,size,newList,list.size());
            }
            else if(propertyValueId!=null){
                list = shoesHeaderService.listUnderProVal(propertyValueId,pageCode,size);
                pb = pageSevice.setTopageBean(pageCode,size,list,shoesHeaderService.getFGoodsVoCountByPvId(propertyValueId));
            }
        }
        else if(filterFlag == 1){
            if(catalogId!=null){
                //获得此catalogId下的所有鞋
                list =  propertyFilterServie.filterCatalog(filterList,catalogId);
                List<FGoodsVo> newList = ListHandleUtils.getPartOfList(list,pageCode,size);
                pb = pageSevice.setTopageBean(pageCode,size,newList,list.size());
            }
            else if(propertyValueId!=null){
                list = propertyFilterServie.filterPropVal(filterList,propertyValueId,pageCode,size);
                pb = pageSevice.setTopageBean(pageCode,size,list,propertyFilterServie.getCountFilterPropVal(filterList,propertyValueId));
            }
        }
        System.out.println(pb);
        //map.put("pageOfShoes", pb);
        return Result.success(pb);
    }
    //分页（针对的是商品页搜索）
    @RequestMapping("/getSearchListByPage")
    @ResponseBody
    public Result getSearchListByPage(Integer pageCode,Integer size,String value,String filterList,Integer filterFlag){
        pageBean pb = new pageBean();
        if(filterFlag==0){
            List<FGoodsVo>  list = searchService.search(value,pageCode,size);
            pb = pageSevice.setTopageBean(pageCode,size,list,searchService.searchByNameCount(value));
        }
        else if(filterFlag == 1){
            List<FGoodsVo>  list1 = propertyFilterServie.filterSearch(filterList,value, pageCode,size);
            pb = pageSevice.setTopageBean(pageCode,size,list1,propertyFilterServie.getCountFilterSearch(filterList,value));
        }

        return Result.success(pb);
    }
}
