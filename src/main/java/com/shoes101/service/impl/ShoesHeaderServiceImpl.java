package com.shoes101.service.impl;

import com.shoes101.mapper.PropertyMapper;
import com.shoes101.mapper.ShoesMapper;
import com.shoes101.mapper.ShoescatalogMapper;
import com.shoes101.mapper.ShoespicMapper;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.pojo.Shoes;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.redis.FGoodsKey;
import com.shoes101.redis.RedisService;
import com.shoes101.result.Result;
import com.shoes101.service.ShoesHeaderService;
import com.shoes101.vo.CatalogInfoVo;
import com.shoes101.vo.FGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShoesHeaderServiceImpl implements ShoesHeaderService {


    /**
     *     里面存放map，map对catalog节点进行了封装，包含内容
     *
     *     {
     *         catalogInfo：{}//catalog 的 实体
     *         childrenIndex[] // 儿子节点在allCatalogInfoList的数组下标
     *     }
     *     ---------------------------分割线----------------------------------
     *
     *     10.1更新了最新方案，上述描述不用看
     *
     *     最新理念：
     *     1、引入level参数，代表某节点往下找level层，比如level为2，代表范围在某节点以下两层范围下搜索或寻找
     *
     *     2、levelList，下面用到的一个list，代表level范围内的叶子节点或者范围内的最底层节点
     *
     *
     */

/*
    public static List<Map<String,Object>> allCatalogInfoList = new ArrayList<Map<String, Object>>();

    public static List<Map<String,Object>> parentCatalogInfoList = new ArrayList<Map<String, Object>>();
*/

    public static List<CatalogInfoVo> neededList = new ArrayList<CatalogInfoVo>();


    @Autowired
    private ShoescatalogMapper shoescatalogMapper;
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private ShoesMapper shoesMapper;
    @Autowired
    private ShoespicMapper shoespicMapper;
    @Autowired
    private RedisService redisService;

    /**
     * 将所有的catalog节点全部放在一个map里，map里面包含的集合内容包含catalogInfo，childrenIndex
     * 然后将map放到allCatalogInfoList里面，未对allCatalogInfoList里的map的childrenIndex进行深度封装,相当于初始化allCatalogInfoList
     */

    /*public void initAllCatalogInfoList(){
        List<Shoescatalog> allList = shoescatalogMapper.selectAll();
        System.out.println("allList："+allList);
        for(Shoescatalog shoescatalog:allList){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("catalogInfo",shoescatalog);
            map.put("childrenIndex",new ArrayList<Integer>());
            allCatalogInfoList.add(map);
        }
        System.out.println(allCatalogInfoList.toString());
    }

    //找到某节点的儿子节点在allCatalogInfoList里的位置
    public List<Integer> findChildrenIndex(Shoescatalog shoescatalog){
        List<Integer> childrenIndex = new ArrayList<Integer>();
        //获取节点id
        Integer pid = shoescatalog.getCatalogid();
        //以下用顺序查找，本人不才，其他查找忘记了，谁会其它牛逼的，可以改！！

        //查找在allCatalogInfoList中map里的catalogInfo里的parentid与pid相同的位置

        for(int i = 0;i<allCatalogInfoList.size();i++){
            Shoescatalog tempCatalog = (Shoescatalog) allCatalogInfoList.get(i).get("catalogInfo");
            if(pid == tempCatalog.getParentid()){
                childrenIndex.add(i);
            }
        }
        return childrenIndex;
    }


    //封装allCatalogInfoList里的map的儿子节点索引，即childrenIndex
    public void setIndexToMap(){
        //遍历allCatalogInfoList
        for(Map<String,Object> map:allCatalogInfoList){
            //得到map里面的catalogInfo
            Shoescatalog shoescatalog = (Shoescatalog) map.get("catalogInfo");
            //如果parentid为0，即父节点，放到parentCatalogInfoList里
            if(shoescatalog.getParentid()==0 && shoescatalog.getParentid()!=null){
                parentCatalogInfoList.add(map);
            }
            //更新map的childrenIndex，设置子节点在allCatalogInfoList的位置
            map.put("childrenIndex",findChildrenIndex((Shoescatalog) map.get("catalogInfo")));
            System.out.println(map.toString());
        }
    }*/


   // ----------------------------分割线------------------------------------------------
    /**
     *
     *   上述注释的方法是10.1日之前的方法，已作废。与队友俊鑫分析兼讨论后得出了下面的方法
     *
     */

    //实现NavBarService里的一个方法
    @Override
    public List<CatalogInfoVo> getCatalogInfo(Shoescatalog shoescatalog ,Integer level) {
        //清空neededList
        neededList.clear();
        System.out.println("一开始的needList"+neededList);
        //level
        List<CatalogInfoVo> levelList = new ArrayList<CatalogInfoVo>();
        Map<String,List<CatalogInfoVo>> map = new HashMap<String, List<CatalogInfoVo>>();
        levelList =  getLevelList(shoescatalog,level,levelList);
        //System.out.println("经过getLevelList的levelList:"+levelList.toString());
        levelList = HandleLevelList(levelList,level,shoescatalog);
       // System.out.println("经过HandleLevelList的levelList"+levelList);
        List<CatalogInfoVo> newList = new ArrayList<CatalogInfoVo>();
        newList  = (List<CatalogInfoVo>) ((ArrayList<CatalogInfoVo>) levelList).clone();
        //System.out.println("needList = "+neededList.toString());
        newList  = combineList(neededList,newList);
        return newList;
    }

    //根据名字和parentId搜Shoescatalog
    @Override
    public Shoescatalog selectByNameAndParentId(Integer parentId,String catalogName) {
        return (Shoescatalog) shoescatalogMapper.selectByNameAndParentId(parentId,catalogName);
    }
    @Override
    public Map<String, List<CatalogInfoVo>> initCatalogInfo() {
        Map<String,List<CatalogInfoVo>> map = new HashMap<String, List<CatalogInfoVo>>();
        String catalogName[] ={"男鞋","女鞋"};
        for(int i = 0;i<catalogName.length;i++){
            Shoescatalog shoescatalog = this.selectByNameAndParentId(0, catalogName[i]);
            List<CatalogInfoVo> list = this.getCatalogInfo(shoescatalog,2);
            map.put((String) catalogName[i],list);
        }
        return map;
    }
    //处理点击导航栏分类部分的处理
    @Override
    public List<FGoodsVo> listUnderCatalog(Integer catalogId) {
        //取缓存
        List<FGoodsVo> fGoodsVoList = redisService.get(FGoodsKey.getGoodsListCatalog,""+catalogId,true,FGoodsVo.class);
        if(fGoodsVoList==null){
            //取数据库
            Shoescatalog shoescatalog = shoescatalogMapper.selectByPrimaryKey(catalogId);
            //System.out.println("111"+shoescatalog);
            List<Shoescatalog> leafList = this.getLeafList(new ArrayList<Shoescatalog>(),shoescatalog);
            List<FGoodsVo> list = new ArrayList<FGoodsVo>();
            for(Shoescatalog shoescatalog1 : leafList){
                List<FGoodsVo> tempList = shoesMapper.getShoesIdAndNameAndPriceByCatalogId(shoescatalog1.getCatalogid());
                System.out.println("tt"+tempList);
                for(int i = 0;i < tempList.size();i++)
                {
                    tempList.get(i).setPics(shoesMapper.getAllPicById(tempList.get(i).getShoesid()));
                }
                for(FGoodsVo fGoodsVo:tempList){
                    list.add(fGoodsVo);
                }
            }
            redisService.set(FGoodsKey.getGoodsListCatalog,""+catalogId,list);
            return list;
        }
        else{
            return fGoodsVoList;
        }
    }

    //对应处理点击导航栏品牌部分的处理
    @Override
    public List<FGoodsVo> listUnderProVal(Integer propertyValueId) {
        //取缓存
        List<FGoodsVo> list = redisService.get(FGoodsKey.getGoodsListProVal,""+propertyValueId,true,FGoodsVo.class);
        if(list==null){
            //取数据库
            list =  shoesMapper.getFGoodsVoByPvId(propertyValueId);//分页处理
            for(FGoodsVo fGoodsVo:list){
                fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
            }
            redisService.set(FGoodsKey.getGoodsListProVal,""+propertyValueId,list);
        }
        return list;
    }

    @Override
    public List<FGoodsVo> listUnderProVal(Integer propertyValueId, Integer pageCode, Integer size) {
        Integer start = (pageCode-1)*size;
        return shoesMapper.getFGoodsVoByPvIdPage(propertyValueId,start,size);
    }

    //根据品牌获得相应的鞋的数量
    @Override
    public Integer getFGoodsVoCountByPvId(Integer propertyValueId) {
        return shoesMapper.getFGoodsVoCountByPvId(propertyValueId);
    }

    @Override
    public List<Shoescatalog> getLeafList(List<Shoescatalog> leafList, Shoescatalog shoescatalog) {
        if(shoescatalog.getIsleaf()  == 1){
            //Shoescatalog catalog =  shoescatalogMapper.selectByPrimaryKey(catalogId);
            leafList.add(shoescatalog);
            return leafList;
        }
        else {
            Integer parentId = shoescatalog.getCatalogid();
            //System.out.println(parentId);
            List<Shoescatalog> list1 = (List<Shoescatalog>) shoescatalogMapper.findCatalogByPid(parentId);
            //System.out.println(list1);
            if (list1.size() != 0) {
                for (int i = 0; i < list1.size(); i++) {
                    Shoescatalog tempCatalog = list1.get(i);
                    leafList = getLeafList(leafList, tempCatalog);
                }
            }
        }
        return leafList;
    }



    //获得某节点往下level层内的叶子节点或到某节点往下数level层的那一层的节点
    public List<CatalogInfoVo> getLevelList( Shoescatalog shoescatalog,Integer level,List<CatalogInfoVo> levelList) {

        if(shoescatalog.getIsleaf()  == 1){
            levelList.add(initLevelCatalogInfoVo(shoescatalog));
            return levelList;
        }
        else{
            if(level!=0){
                Integer parentId = shoescatalog.getCatalogid();
                List<Shoescatalog> list1 = (List<Shoescatalog>) shoescatalogMapper.findCatalogByPid(parentId);
                if (list1.size() != 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        Shoescatalog tempCatalog = list1.get(i);
                        //当level变为1，即递归到第level层时，add进去
                        if(level==1&&tempCatalog.getIsleaf()==0){
                            levelList.add(initLevelCatalogInfoVo(tempCatalog));
                        }
                        //System.out.println(levelList);
                        Integer levelRecord = level-1;
                        levelList = getLevelList( tempCatalog, levelRecord,levelList);
                        //System.out.println(levelList);
                    }
                }
            }
        }

        return levelList;
    }
    CatalogInfoVo initLevelCatalogInfoVo(Shoescatalog catalog){
        CatalogInfoVo catalogInfoVo = new CatalogInfoVo();
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("childList",new ArrayList<CatalogInfoVo>());
        map.put("ifRecord",0);
        catalogInfoVo.setChildMapData(map);
        catalogInfoVo.setIsLeaf(catalog.getIsleaf());
        catalogInfoVo.setShoescatalog(catalog);
        return catalogInfoVo;
    }

    List<CatalogInfoVo> HandleLevelList(List<CatalogInfoVo> levelList,Integer level,Shoescatalog shoescatalog){
        System.out.println("此时的level"+level);
       if(level==1){
           return levelList;
       }else {
           HashMap<Integer,List<CatalogInfoVo>> map = (HashMap<Integer, List<CatalogInfoVo>>) classifyLeveList(levelList,shoescatalog);
           levelList.clear();
           int i  = 1;
           //遍历map
           for(Map.Entry<Integer, List<CatalogInfoVo>> entry: map.entrySet())
           {
               System.out.println("第"+i+"次循环："+"Key: "+ entry.getKey()+ " Value: "+entry.getValue());
               //根据key找节点
               Shoescatalog pCatalog = shoescatalogMapper.selectByPrimaryKey(entry.getKey());
               Map<String,Object> hm = new HashMap<String,Object>();
               CatalogInfoVo catalogInfoVo = new CatalogInfoVo();
               hm.put("ifRecord",1);
               hm.put("childList",entry.getValue());
               catalogInfoVo.setChildMapData(hm);
               catalogInfoVo.setShoescatalog(pCatalog);
               catalogInfoVo.setIsLeaf(pCatalog.getIsleaf());
               //更新levelList
               levelList.add(catalogInfoVo);
               i++;
           }
           if(levelList.get(0).getShoescatalog().getParentid()==shoescatalog.getCatalogid()){
               return levelList;
           }
           levelList = HandleLevelList(levelList,--level,shoescatalog);
           return levelList;
       }
    }

    Map<Integer,List<CatalogInfoVo>> classifyLeveList(List<CatalogInfoVo> levelList,Shoescatalog shoescatalog){

        Map<Integer, List<CatalogInfoVo>> map = new HashMap<Integer, List<CatalogInfoVo>>();
        for (CatalogInfoVo catalogInfoVo : levelList){
            // map是否包含此key，若已经包含则添加一个新的CatalogInfoVo到对应value集合中
            Integer parentid = catalogInfoVo.getShoescatalog().getParentid();
            //System.out.println(parentid);
            if (map.containsKey(parentid)){
                map.get(parentid).add(catalogInfoVo);
            }else{
                // map不包含此key，则重新创建一个新集合，并把这个数字添加进集合
                // ，再把集合放到map中
                if(parentid==shoescatalog.getCatalogid()){
                    neededList.add(catalogInfoVo);
                }
                else{
                    List<CatalogInfoVo> newList = new ArrayList<CatalogInfoVo>();
                    newList.add(catalogInfoVo);
                    map.put(parentid, newList);
                }
            }
        }
        return map;
    }

    //把两个list连起来
    List<CatalogInfoVo> combineList(List<CatalogInfoVo> list1,List<CatalogInfoVo> list2){
        for(CatalogInfoVo catalogInfoVo:list1){
            list2.add(catalogInfoVo);
        }
        return list2;
    }

}
