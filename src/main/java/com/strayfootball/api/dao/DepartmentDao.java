package com.strayfootball.api.dao;


import com.strayfootball.api.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * 部门数据访问层
 * @author ID_YiQuan
 */
@Repository
public interface DepartmentDao {
    /**
     * 删除
     * @param id 主键
     * @return
     */
    int deleteById(Integer id);

    /**
     * 插入数据
     * @param record 实体数据
     * @return
     */
    int insert(Department record);

    /**
     *插入选择性
     * @param record 实体数据
     * @return
     */
    int insertSelective(Department record);

    /**
     * 查询一条数据
     * @param id 主键
     * @return
     */
    Department findById(Integer id);

    /***
     * 查询
     * @param leaderMemberId leaderMemberId
     * @return Department
     */
    Department findByLeaderMemberId(Integer leaderMemberId);

    /**
     * 按主要主键选择更新
     * @param record 实体数据
     * @return
     */
    int updateByIdSelective(Department record);

    /**
     * 修改数据
     * @param record 实体数据
     * @return
     */
    int updateById(Department record);


    /**
     * 查询列表数据
     * @param parentId 父级主键
     * @return
     */
    ArrayList<Department> findListByParentId(Integer parentId);
    /**
     * 获取部门领导 ID
     * @return
     */
    ArrayList<Integer> findDepartmentLeaderIdList();

}