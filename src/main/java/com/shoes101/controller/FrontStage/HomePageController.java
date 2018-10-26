package com.shoes101.controller.FrontStage;

import com.shoes101.mapper.ShoesMapper;
import com.shoes101.result.Result;
import com.shoes101.service.HomePageService;
import com.shoes101.service.ShoesHeaderService;
import com.shoes101.vo.FGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class HomePageController {


    @Autowired
    private HomePageService homePageService;
    @Autowired
    private ShoesHeaderService shoesHeaderService;

    // 域名跳转至首页
    @RequestMapping("/")
    public String goHomePage(){
        /*List<FGoodsVo> newestGoods = homePageService.getNewestGoods(10);
        System.out.println(newestGoods);*/
        return"front/index";
    }

    //得到最新的商品,用参数区分是男鞋的最新还是女鞋的最新
    @RequestMapping("/getNewestGoods")
    @ResponseBody
    public Result getDefaultNewestGoods(Integer count, String name, Integer parentId){
        //得到对应的catalogId
        Integer catalogId = shoesHeaderService.selectByNameAndParentId(parentId,name).getCatalogid();
        return Result.success(homePageService.getNewestGoods( count,catalogId));
    }
    //当月热销
    @RequestMapping("/getHotSale")
    @ResponseBody
    public Result getHotSale(Integer count){
        return Result.success(homePageService.getHotSale(count));
    }

    //最旧商品
    @RequestMapping("/getOldestGoods")
    @ResponseBody
    public Result getOldestGoods(Integer count){

        return Result.success(homePageService.getOldestGoods(count));
    }

}
