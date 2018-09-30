package com.shoes101.service.impl;

import com.shoes101.mapper.ShoescatalogMapper;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.service.NavBarService;
import com.shoes101.vo.CatalogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NavBarServiceImpl implements NavBarService {


    /**
     *     里面存放map，map对catalog节点进行了封装，包含内容
     *
     *     {
     *         catalogInfo：{}//catalog 的 实体
     *         childrenIndex[] // 儿子节点在allCatalogInfoList的数组下标
     *     }
     */

    public static List<Map<String,Object>> allCatalogInfoList = new ArrayList<Map<String, Object>>();

    public static List<Map<String,Object>> parentCatalogInfoList = new ArrayList<Map<String, Object>>();

    @Autowired
    private ShoescatalogMapper shoescatalogMapper;

    @Override
    public CatalogInfoVo getCatalogInfo() {

        //初始化allCatalogInfoList
        initAllCatalogInfoList();
        //
        setIndexToMap();

        //封装到CatalogInfoVo
        CatalogInfoVo catalogInfoVo = new CatalogInfoVo();
        catalogInfoVo.setAllCatalogInfo(allCatalogInfoList);
        catalogInfoVo.setParentCatalogInfo(parentCatalogInfoList);

        return catalogInfoVo;
    }

    /**
     * 将所有的catalog节点全部放在一个map里，map里面包含的集合内容包含catalogInfo，childrenIndex
     * 然后将map放到allCatalogInfoList里面，未对allCatalogInfoList里的map的childrenIndex进行深度封装,相当于初始化allCatalogInfoList
     */
    //
    public void initAllCatalogInfoList(){
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

    }
}
