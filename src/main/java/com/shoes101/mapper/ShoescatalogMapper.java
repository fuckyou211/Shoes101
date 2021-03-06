package com.shoes101.mapper;

import com.shoes101.pojo.Shoescatalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ShoescatalogMapper {

    int deleteByPrimaryKey(Integer catalogid);

    int insert(Shoescatalog record);

    Shoescatalog selectByPrimaryKey(Integer catalogid);

    List<Shoescatalog> selectAll();

    int updateByPrimaryKey(Shoescatalog record);

    @Select( " select * from shoescatalog where parentId = #{parentId}")
    List<Shoescatalog> findCatalogByPid(@Param("parentId") Integer  parentId);

    @Select("select * from shoescatalog where parentid = #{parentId} and catalogname = #{catalogName}")
    Shoescatalog selectByNameAndParentId(@Param("parentId") Integer parentId,@Param("catalogName") String catalogName);
}