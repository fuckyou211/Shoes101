package com.shoes101.mapper;

import com.shoes101.pojo.Rushbuy;
import com.shoes101.vo.RushShoesskuAndPropvnameVo;
import com.shoes101.vo.RushSkuAndQtyVo;
import com.shoes101.vo.RushbuyVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RushbuyMapper {
    int deleteByPrimaryKey(Integer rushbuyid);

    int insert(RushbuyVo record);

    Rushbuy selectByPrimaryKey(Integer rushbuyid);

    List<Rushbuy> selectAll();

    int updateByPrimaryKey(Rushbuy record);

    //根据rushbuy中的商品id获取商品名
    public List<RushbuyVo> getAllFromRb();

    //获取图片
    String getPic(@Param("shoesid") int shoesid);

    //获取最新插入到rushbuy的id
    int getLateId();

    //删除抢购活动
    int deleteRush(@Param("rushbuyid") int rushbuyid);

    //删除对应的库存
    int deleteRushsku(@Param("rushbuyid") int rushbuyid);

    //前台 获取全部抢购活动
    List<RushbuyVo> getAllRush();

    //前台 根据id获取抢购活动
    RushbuyVo getOneRush(@Param("rushbuyid") int rushbuyid);

    //前台 获取与rushbuyid有关的rushsku中的库存
    List<RushSkuAndQtyVo> getSkuAndQty(@Param("rushbuyid") int rushbuyid);

    //前台 根据商品id获取名字
    String getShoesname(@Param("shoesid")int shoesid);

    //根据skuid返回RushShoesskuAndPropvnameVo
    RushShoesskuAndPropvnameVo getSoOn(@Param("skuid") int skuid);
    @Select("select * from rushbuy where shoesid = #{shoesid} ")
    public RushbuyVo getRushbuyByGoodid(@Param("shoesid") Integer shoesid);

    //根据skuid获取属性
    String getProperty(@Param("skuid") int skuid);

    //根据skuid获取图片 保险起见 limit 1
    String getSkuPic(@Param("skuid") int skuid);

    //获取大图
    String getBigPic(@Param("shoesid") int shoesid);

}