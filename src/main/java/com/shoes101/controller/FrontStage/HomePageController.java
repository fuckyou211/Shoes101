package com.shoes101.controller.FrontStage;

import com.shoes101.service.impl.HomePageService;
import com.shoes101.vo.FGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomePageController {


    @Autowired
    HomePageService homePageService;
    @RequestMapping("/ShoesShop/index")
    public String toHomePage(){
        List<FGoodsVo> newestGoods = homePageService.getNewestGoods(10);
        return"/front/index";
    }

    // 域名跳转至首页
    @RequestMapping("/")
    public String goHomePage(){
        List<FGoodsVo> newestGoods = homePageService.getNewestGoods(10);
        return"/front/index";
    }

    //得到最新的商品
    @RequestMapping("/getDefaulNewestGoods")
    @ResponseBody
    public List<FGoodsVo> getDefaultNewestGoods(Integer count){
        return homePageService.getNewestGoods(count);
    }
}
