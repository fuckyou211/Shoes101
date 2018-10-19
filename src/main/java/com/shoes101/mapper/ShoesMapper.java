package com.shoes101.mapper;

import com.shoes101.pojo.Shoes;
import com.shoes101.pojo.Shoespic;
import com.shoes101.pojo.Shoessku;
import com.shoes101.pojo.Splink;
import com.shoes101.vo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    //当颜色或者尺码传入为空时 根据Id 非空的属性来获取总库存
    int getQty3(@Param("shoesid") Integer shoesid, @Param("str") String str);

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

    int getSkuOfShoes(@Param("shoesid") int shoesid);

    //将库存quantity shoesid skuid 存入到rushsku表中
    int sendInRushsku(@Param("quantity") int quantity,@Param("shoesid") int shoesid,@Param("skuid") int skuid,@Param("rushbuyid") int rushbuyid);


    /**
     * @auth:Vakoe
     */
    //获取商品名、商品价格根据catalogId
    @Select("select DISTINCT shoes.shoesid,shoes.shoesname,shoessku.price from shoes INNER JOIN shoessku on shoes.shoesid=shoessku.shoesid where shoessku.quantity > 0 and shoes.isdropoff=1 and shoes.catalogid = #{catalogId}")
    List<FGoodsVo> getShoesIdAndNameAndPriceByCatalogId(@Param("catalogId")Integer catalogId);

    //获得最新的Shoes(FGoodsVo此时里面并没有pic)
    @Select("select DISTINCT shoes.shoesid,shoes.shoesname,shoessku.price from shoes INNER JOIN shoessku on shoes.shoesid=shoessku.shoesid where shoessku.quantity > 0 and shoes.isdropoff=1 order by shoes.adddate desc limit 0,#{size} ")
    List<FGoodsVo> getFGoodsVoOrderByDate(@Param("size") Integer size);
    //根据品牌获得相应的鞋
    @Select("select DISTINCT a.shoesid,a.shoesname,b.price from shoes as a INNER JOIN " +
            "(select shoessku.* from shoessku LEFT JOIN splink on shoessku.shoesid = splink.shoesid " +
            "where splink.propertyvalueid = #{propertyValueId}) as b on a.shoesid = b.shoesid where b.quantity>0 and a.isdropoff=1 limit #{begin},#{size}")
    List<FGoodsVo> getFGoodsVoByPvId(@Param("propertyValueId") Integer propertyValueId,@Param("begin")Integer begin,@Param("size") Integer size);
    //根据品牌获得相应的鞋的数量
    @Select("select count(DISTINCT a.shoesid) from shoes as a INNER JOIN " +
            "(select shoessku.* from shoessku LEFT JOIN splink on shoessku.shoesid = splink.shoesid " +
            "where splink.propertyvalueid = #{propertyValueId}) as b on a.shoesid = b.shoesid where b.quantity>0 and a.isdropoff=1")
    Integer getFGoodsVoCountByPvId(@Param("propertyValueId") Integer propertyValueId);

}