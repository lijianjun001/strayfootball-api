package com.strayfootball.api.dao;


import com.strayfootball.api.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * 人员数据访问
 * @author ID_YiQuan
 */
@Repository
public interface MemberDao {
    /**
     * 删除
     * @param id 主键id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 插入数据
     * @param record 实体数据
     * @return
     */
    int insert(Member record);

    /**
     * 插入选择性
     * @param record 实体数据
     * @return
     */
    int insertSelective(Member record);

    /**
     * 查询数据
     * @param id 主键id
     * @return
     */
    Member findById(Integer id);

    /***
     * 根据手机号查询
     * @param phone
     * @return
     */
    Member findByPhone(String phone);

    /**
     * 选择性修改
     * @param record 实体数据
     * @return
     */
    int updateByIdSelective(Member record);

    /**
     * 修改数据
     * @param record 实体数据
     * @return
     */
    int updateById(Member record);

    /**
     * 查询数据
     * @param departmentId 主键
     * @return
     */
    ArrayList<Member> findByDepartmentId(Integer departmentId);
}