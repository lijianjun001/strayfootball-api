package com.strayfootball.api.service;

import com.strayfootball.api.dto.request.plan.TrainingPlanRequest;
import com.strayfootball.api.dto.response.plan.PlanViewModel;
import com.strayfootball.api.entity.TrainingPlan;

import java.util.List;

/**
 * description
 *训练项目服务类
 * @author Karl
 * @create 2019/6/2 10:26
 */
public interface TrainingPlanService {


    /**
     * 按条件获取所有信息
     * @param request
     * @return
     */
    List<TrainingPlan> list(TrainingPlanRequest request);

    /**
     * 添加
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    void add(int userId, TrainingPlanRequest request);

    /**
     * 修改
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    void update(int userId, TrainingPlanRequest request);


    /**
     * 删除
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    void delete(int userId, TrainingPlanRequest request);

    /**
     * 详细
     *
     * @param request
     * @return
     */
    TrainingPlan detailed(TrainingPlanRequest request);

    /**
     * 获取所有的key和value  供sqlect标签使用
     *
     * @return
     */
    PlanViewModel listKey();
}
