package com.shoes101.mapper;

import com.shoes101.pojo.Shoes;
import com.shoes101.pojo.Shoespic;
import com.shoes101.pojo.Shoessku;
import com.shoes101.pojo.Splink;
import com.shoes101.vo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShoesMapper {
    int deleteByPrimaryKey(Integer shoesid);

    int insert(Shoes record);

    Shoes selectByPrimaryKey(Integer shoesid);

    List<Shoes> selectAll();

    int updateByPrimaryKey(Shoes record);

    //获取所有鞋类
    public List getAllShoes();

    @Update("update  shoes set isdropoff=#{isdropoff} where shoesid=#{shoesid}")
    int selectupdateisdropoff(@Param("shoesid") Integer shoesid, @Param("isdropoff") String isdropoff);

//    //获取所有的商品id
//    List<Integer> getAllShoesId();

    //获取商品名、商品价格
    List<FGoodsVo> getAllShoesIdAndNameAndPrice();

    //获取商品图片
    List<String> getAllPicById(@Param("shoesid") Integer shoesid);

    //根据商品id获取商品、库存、价格、详情
    FDetailsVo getDetails(@Param("shoesid") Integer shoesid);

    //根据商品id获取所有的skuid
    List<Integer> getSkuid(@Param("shoesid") Integer shoesid);

    //根据skuid获取尺码
    List<String> getSizeById(@Param("skuid") Integer skuid);

    //根据skuid获取图片
    List<String> getPicById(@Param("skuid") Integer skuid);

    //根据shoesid获取属性
    List<Splink> getPidById(@Param("shoesid") Integer shoesid);

    //根据商品id和颜色属性获取商品的skuid 用于下一步根据skuid获取到第一个商品图片 商品详情页的颜色小图片显示
    int getTheSkuid(@Param("shoesid") Integer shoesid,@Param("str") String str);

    //根据skuid获取图片
    String getColorPicForDetail(@Param("skuid") Integer skuid);

    //根据id 颜色和属性获取库存
    int getQty(@Param("shoesid") Integer shoesid,@Param("str") String str);

    //根据id 颜色和属性获取库存和skuid
    SkuIdAndQtyVo getQty2(@Param("shoesid") Integer shoesid, @Param("str") String str);

    //根据id获取商品描述
    String getShoesDetail(@Param("shoesid") Integer shoesid);

    //根据商品id获取所有大图
    List<String> getAllPic(@Param("shoesid") Integer shoesid);

    //根据商品id获取分类id
    int getShoesCatalog(@Param("shoesid") Integer shoesid);

    //根据上面方法获取的catalogid获取分类名和父分类编号
    CatalognameAndParentVo getCatalognameAndFather(@Param("catalogid") Integer catalogid);

    //根据商品id计算总库存
    int calTotalQty(@Param("shoesid") Integer shoesid);

    //少一个条件时的库存
    int calQtyWithoutOne(@Param("shoesid") Integer shoseid,@Param("cdn") String cdn);

    //获取抢购用所有鞋类
    public List<RushMVo> getShoesForRush();

    //获取选取鞋类的所有库存
    public List<ShoesskuAndPropvnameVo> getShoesskuForRush(@Param("shoesid") int shoesid);

    //根据shoesid获取shoessku里的相关库存数量
    int getCountOfShoes(@Param("shoesid") int shoesid);

    //将库存quantity shoesid skuid 存入到rushsku表中
    int sendInRushsku(@Param("quantity") int quantity,@Param("shoesid") int shoesid,@Param("skuid") int skuid);


}