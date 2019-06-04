package com.strayfootball.api.dao;

import com.strayfootball.api.entity.Roles;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}