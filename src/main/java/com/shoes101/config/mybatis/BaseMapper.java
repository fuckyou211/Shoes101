package com.shoes101.config.mybatis;

/*
    通用Mapper  不要添加任何的注释
    只能支持单表操作

    Select
    方法：List<T> select(T record);
    说明：根据实体中的属性值进行查询，查询条件使用等号

    方法：T selectByPrimaryKey(Object key);
    说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号

    方法：List<T> selectAll();
    说明：查询全部结果，select(null)方法能达到同样的效果

    方法：T selectOne(T record);
    说明：根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号

    方法：int selectCount(T record);
    说明：根据实体中的属性查询总数，查询条件使用等号

    Insert
    方法：int insert(T record);
    说明：保存一个实体，null的属性也会保存，不会使用数据库默认值

    方法：int insertSelective(T record);
    说明：保存一个实体，null的属性不会保存，会使用数据库默认值

    Update
    方法：int updateByPrimaryKey(T record);
    说明：根据主键更新实体全部字段，null值会被更新

    方法：int updateByPrimaryKeySelective(T record);
    说明：根据主键更新属性不为null的值

    Delete
    方法：int delete(T record);
    说明：根据实体属性作为条件进行删除，查询条件使用等号

    方法：int deleteByPrimaryKey(Object key);
    说明：根据主键字段进行删除，方法参数必须包含完整的主键属性

    Example方法
    方法：List<T> selectByExample(Object example);
    说明：根据Example条件进行查询
    重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列

    方法：int selectCountByExample(Object example);
    说明：根据Example条件进行查询总数

    方法：int updateByExample(@Param("record") T record, @Param("example") Object example);
    说明：根据Example条件更新实体record包含的全部属性，null值会被更新

    方法：int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);
    说明：根据Example条件更新实体record包含的不是null的属性值

    方法：int deleteByExample(Object example);
    说明：根据Example条件删除数据

 */

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;


public interface BaseMapper<T> extends Mapper<T>
{

}
