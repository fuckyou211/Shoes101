package com.shoes101.service.impl;

import com.shoes101.access.UserContext;
import com.shoes101.mapper.ShopcartMapper;
import com.shoes101.mapper.ShopcartdetailsMapper;
import com.shoes101.pojo.Shopcart;
import com.shoes101.pojo.Shopcartdetails;
import com.shoes101.pojo.User;
import com.shoes101.service.ShopCartService;
import com.shoes101.vo.ShopCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopcartMapper shopcartMapper;

    @Autowired
    private ShopcartdetailsMapper scdmapper;

    private static User getuser(){
        return UserContext.getUser();
    }

    @Override
    public List<ShopCartVo> addShopCart(ShopCartVo shopCartVo) {

        Shopcartdetails shopcartdetails = new Shopcartdetails();
        //新建订单详情并赋值
        shopcartdetails.setScid(userShopcart().getScid());//将购物车的id关联到购物车详情
        shopcartdetails.setSkuid(shopCartVo.getSkuid());//skuid
        shopcartdetails.setQuantity(shopCartVo.getCount());//数量
        shopcartdetails.setAdddate(new Date());//日期
        shopcartdetails.setPrice(shopCartVo.getPrice());//单价

        //存到数据库,更新购物车详情
        scdmapper.insert(shopcartdetails);

        //同时也更新购物车的总价格

        Shopcart shopcart = userShopcart();
        shopcart.setSctotalprice(getShopCartPrice(getUserShopCart()));
        shopcartMapper.updateByPrimaryKey(shopcart);

        //再从新获取当前用户的购物车
        return getUserShopCart();
    }

    @Override
    public List<ShopCartVo> getUserShopCart() {

        //先得到用户，再得到购物车
        //得到购物车，再得到购物车详情List
        List<Shopcartdetails> details = scdmapper.selectByscid(userShopcart().getScid());
        //将List封装进Ov类
        List<ShopCartVo> ovList= new ArrayList<ShopCartVo>();

        for(Shopcartdetails item : details){
            ShopCartVo shopCartVo= new ShopCartVo();

            shopCartVo.setQuantity(item.getQuantity());//数量
            shopCartVo.setCount(item.getQuantity());
            shopCartVo.setSkuid(item.getSkuid());//skuid
            shopCartVo.setScdid(item.getScdid());//购物车id
            shopCartVo.setPrice(item.getPrice());
            shopCartVo.setTotalprice(item.getPrice()*item.getQuantity());
            //颜色图片
            shopCartVo.setColorPic(scdmapper.selectcolorpicByid(item.getSkuid()));

            //先根据 skuid 找到 shoesid，接着再根据 shoesid 找到 name

            int shoesid = scdmapper.selectShoesidBySkuid(item.getSkuid());
            shopCartVo.setShoesid(shoesid);
            shopCartVo.setShoesName(scdmapper.selectShoesnameByShoesid(shoesid));
            // 再根据skuid 返回 skuproperty
            String skuproperty = scdmapper.selectSkupropertyBySkuid(item.getSkuid());

            //将得到的字符串解析为颜色和尺码 得到的格式：{d:d,d:d},d 为int型的id

            skuproperty = skuproperty.replace("{", "").replace("}","");
            String[] strs = skuproperty.split(",");

            String propcolor[]=strs[0].split(":");
            int colorid= Integer.parseInt(propcolor[1]);

            String propsize[]=strs[1].split(":");
            int sizeid= Integer.parseInt(propsize[1]);

            System.out.println("世上无难事: colorid ="+colorid+" sizeid = "+sizeid);

            //再获取该sku对应的propertyvalue（颜色和尺码）
            shopCartVo.setColor(scdmapper.selectPropValByPropValId(colorid));
            shopCartVo.setSize(scdmapper.selectPropValByPropValId(sizeid));

            ovList.add(shopCartVo);

            //System.out.println(shopCartVo.toString());
        }

        return ovList;
    }

    @Override
    public List<ShopCartVo> editShopCart(ShopCartVo shopCartVo) {
        return null;
    }

    @Override
    public List<ShopCartVo> removeFormCart(List<ShopCartVo> shopCartVos) {
        return null;
    }

    //计算一个购物车详情的总价
    public double getTotalPrice(ShopCartVo shopCartVo){
        return shopCartVo.getCount()*shopCartVo.getPrice();
    }

    /**
     * 获取用户的购物车
     * @return
     */
    public  Shopcart userShopcart(){

        //获取用户的购物车
        Shopcart shopcart;
        System.out.println(getuser().getUserid());
        shopcart=shopcartMapper.getShopCartByUser(getuser().getUserid());

        //假如用户没有购物车，则new一个给他
        if(shopcart==null||shopcart.equals(null)){
            shopcart = new Shopcart();
            shopcart.setUserid(getuser().getUserid());
            shopcartMapper.insert(shopcart);
        }

        return shopcart;
    }

    public double getShopCartPrice(List<ShopCartVo> ovlist){
        double shopCartPrice = 0.0;
        for(ShopCartVo ov :ovlist){
            shopCartPrice+=ov.getTotalprice();
        }
        return shopCartPrice;
    }
}
