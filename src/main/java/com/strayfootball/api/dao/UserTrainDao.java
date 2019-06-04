package com.strayfootball.api.dao;

import com.strayfootball.api.entity.UserTrain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserTrainDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTrain record);

    int insertSelective(UserTrain record);

    UserTrain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTrain record);

    int updateByPrimaryKey(UserTrain record);

    /**
     * 通过训练项目查询出所有球员训练的信息
     * @return
     */
    List<UserTrain> findList();
    /**
     * 通过训练项目查询出所有球员训练的信息
     * @param tainId 训练项目ID
     * @return
     */
    List<UserTrain> findListByTainId(Integer tainId);


    /**
     * 多条件动态查询
     */
    List<UserTrain> selectUserByDynamic(UserTrain userTrain);


    /**
     * 查询 球员成绩，通过计划id
     * @param record
     * @return
     */
    UserTrain findByPlanIdAndUserId(UserTrain record);
}