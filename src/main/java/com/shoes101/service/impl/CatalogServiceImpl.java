package com.shoes101.service.impl;

import com.shoes101.mapper.ShoesMapper;
import com.shoes101.mapper.ShoescatalogMapper;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private  ShoescatalogMapper shoescatalogMapper;

   //获得某节点的所有叶子节点
    @Override
    public List<Shoescatalog> getLeafList(List<Shoescatalog> leafList, Integer parentId, Integer catalogId, Integer isLeaf) {
        if(isLeaf  == 1){
            Shoescatalog catalog =  shoescatalogMapper.selectByPrimaryKey(catalogId);
            leafList.add(catalog);
            return leafList;
        }
        else {
            parentId = catalogId;
            List<Shoescatalog> list1 = (List<Shoescatalog>) shoescatalogMapper.findCatalogByPid(parentId);
            if (list1.size() != 0) {
                for (int i = 0; i < list1.size(); i++) {
                    Shoescatalog tempCatalog = list1.get(i);
                    catalogId = tempCatalog.getCatalogid();
                    parentId = tempCatalog.getParentid();
                    isLeaf = tempCatalog.getIsleaf();
                    leafList = getLeafList(leafList, parentId, catalogId, isLeaf);
                }
            }
        }
        return leafList;
    }

    //获得某节点的儿子节点
    @Override
    public List<Shoescatalog> getChildList(Integer catalogId) {
        Integer parentId = catalogId;
        return shoescatalogMapper.findCatalogByPid(parentId);
    }

    //添加一个分类
    @Override
    public Integer addCatalog(Shoescatalog catalog) {
        return shoescatalogMapper.insert(catalog);
    }

    //删除某节点及以下分类节点的id
    @Override
    public Integer deleteCatalog(Integer catalogId) {
        //获得此id的catalog对象
        Shoescatalog shoescatalog = shoescatalogMapper.selectByPrimaryKey(catalogId);
        System.out.println(shoescatalog);
        List<Shoescatalog> nextList = new ArrayList<Shoescatalog>();
        nextList = this.nextCatalogList(nextList,shoescatalog);
        Integer maxSize = nextList.size();
        Integer result[] = new Integer[maxSize];
        int i = 0;
        int flag  = 0;
        System.out.println(nextList);
       for(Shoescatalog cataLog: nextList)
       {

           result[i] =  shoescatalogMapper.deleteByPrimaryKey(cataLog.getCatalogid());
           if(result[i]>0){
               flag++;
           }
       }
       if(flag==maxSize){
           return 1;
       }
       else return 0;
    }

    //更新修改
    @Override
    public Integer updateCatalog(Shoescatalog catalog) {
        return shoescatalogMapper.updateByPrimaryKey(catalog);
    }

    @Override
    public Shoescatalog catalogPathLast(Integer parentId) {
        Integer cataLogId = parentId;
        Shoescatalog shoescatalog = shoescatalogMapper.selectByPrimaryKey(cataLogId);
        return shoescatalog;
    }

    //获得父级节点
    @Override
    public List<Shoescatalog> getParentCatalog(Integer parentId) {
        return shoescatalogMapper.findCatalogByPid(parentId);
    }

    //处理点击上方路径，实际就是递归向上寻找（待定）

    //获得某节点下的所有分类节点
    public List<Shoescatalog> nextCatalogList(List<Shoescatalog> nextList, Shoescatalog shoescatalog){
        if(shoescatalog.getIsleaf() == 1){
            //Shoescatalog catalog =  shoescatalogMapper.selectByPrimaryKey(catalogId);
            nextList.add(shoescatalog);
            return nextList;
        }
        else {
           Integer parentId = shoescatalog.getCatalogid();
            List<Shoescatalog> list1 = (List<Shoescatalog>) shoescatalogMapper.findCatalogByPid(parentId);
            if (list1.size() != 0) {
                for (int i = 0; i < list1.size(); i++) {
                    Shoescatalog tempCatalog = list1.get(i);
                    nextList.add(tempCatalog);
                    nextList = nextCatalogList(nextList, tempCatalog);
                }
            }
        }
        return nextList;
    }

}