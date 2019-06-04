package com.strayfootball.api.dao;

import com.strayfootball.api.entity.TrainingPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingPlanDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainingPlan record);

    int insertSelective(TrainingPlan record);

    TrainingPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainingPlan record);

    int updateByPrimaryKey(TrainingPlan record);

    /**
     * 获取所有信息
     * @return
     */
    List<TrainingPlan> findList();


    /**
     * 获取所有计划 key 和名称
     * @return
     */
    List<TrainingPlan>  findListKey();


    /**
     * 按条件获取所有信息
     * @return
     */
    List<TrainingPlan>  findListByDynamic(TrainingPlan record);

}