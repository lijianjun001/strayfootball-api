package com.strayfootball.api.dao;

import com.strayfootball.api.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 球队数据成
 */
@Repository
public interface TeamDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);

    /**
     * 获取所有信息
     * @return
     */
    List<Team> findList();


}