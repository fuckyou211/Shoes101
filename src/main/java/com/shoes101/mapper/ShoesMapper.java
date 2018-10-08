package com.shoes101.mapper;

import com.shoes101.pojo.Shoes;
import com.shoes101.pojo.Shoespic;
import com.shoes101.pojo.Shoessku;
import com.shoes101.pojo.Splink;
import com.shoes101.vo.FDetailsVo;
import com.shoes101.vo.FGoodsVo;
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

}