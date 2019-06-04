package com.strayfootball.api.service;
import java.util.List;

/**
 * 接口基类

 * @param <T> 数据库实体
 * @param <T2> 传入实体信息
 *@author Karl 2019年6月1日  22:36:15
 */
public interface IService<T,T2>{
    /**
     * 根据实体中的属性值进行查询, 查询条件使用等号  @param record the record
     *
     * @param request the request
     *
     * @return the list
     */
    List<T> select(T2 request);
    /**
     * 查询全部结果, select(null)方法能达到同样的效果  @return the list
     *
     * @return the list
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性进行查询, 只能有一个返回值, 有多个结果是抛出异常, 查询条件使用等号  @param record the record
     *
     * @param request the request
     *
     * @return the t
     */
    T selectOne(T2 request);


    /**
     * 保存一个实体, null的属性不会保存, 会使用数据库默认值  @param record the record
     *
     * @param request the request
     *
     * @return the int
     */
    int save(T2 request);

    /**
     * 根据主键更新属性不为null的值  @param entity the entity
     *
     * @param request the request
     *
     * @return the int
     */
    int update(T2 request);

    /**
     * 根据实体属性作为条件进行删除, 查询条件使用等号  @param record the record
     *
     * @param request the request
     *
     * @return the int
     */
    int delete(T2 request);

}
