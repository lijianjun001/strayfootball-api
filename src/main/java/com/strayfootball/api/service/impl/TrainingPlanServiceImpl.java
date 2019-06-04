package com.strayfootball.api.service.impl;

import com.strayfootball.api.dao.TrainingPlanDao;
import com.strayfootball.api.dto.request.plan.TrainingPlanRequest;
import com.strayfootball.api.dto.response.plan.PlanView;
import com.strayfootball.api.dto.response.plan.PlanViewModel;
import com.strayfootball.api.entity.TestPhysical;
import com.strayfootball.api.entity.TrainingPlan;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.TrainingPlanService;
import com.strayfootball.api.util.DateUtil;
import com.strayfootball.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description
 * 训练服务
 *
 * @author Karl
 * @create 2019/6/2 9:55
 */
@Service
public class TrainingPlanServiceImpl extends BaseService implements TrainingPlanService {


    private final TrainingPlanDao trainingPlanDao;

    @Autowired
    public TrainingPlanServiceImpl(TrainingPlanDao trainingPlanDao) {
        this.trainingPlanDao = trainingPlanDao;
    }


    /**
     * 按条件获取所有信息
     *
     * @return
     */
    @Override
    public List<TrainingPlan> list(TrainingPlanRequest request) {
        if (null == request) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return trainingPlanDao.findListByDynamic(TrainingPlan.builder().type(request.getType()).title(request.getTitle()).build());
        //return trainingPlanDao.findList();
    }

    /**
     * 添加
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    @Override
    public void add(int userId, TrainingPlanRequest request) {
        isTeacher(userId);
        if (null == request || StringUtil.isNullOrEmpty(request.getTitle()) || StringUtil.isNullOrEmpty(request.getType()) || StringUtil.isNullOrEmpty(request.getTime())) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        trainingPlanDao.insertSelective(TrainingPlan.builder()
                .created(new Date())
                .end(request.getEnd())
                .time(DateUtil.strToDate(request.getTime()))
                .title(request.getTitle())
                .type(request.getType())
                .build());
    }

    /**
     * 修改
     *
     * @param userId  id
     * @param request 请求实体
     * @return
     */
    @Override
    public void update(int userId, TrainingPlanRequest request) {
        isTeacher(userId);
        if (null == request || StringUtil.isNullOrEmpty(request.getTitle()) || StringUtil.isNullOrEmpty(request.getType()) || StringUtil.isNullOrEmpty(request.getTime())) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        trainingPlanDao.updateByPrimaryKeySelective(TrainingPlan.builder().id(request.getId())
                .end(request.getEnd())
                .time(DateUtil.strToDate(request.getTime()))
                .title(request.getTitle())
                .type(request.getType())
                .build());
    }

    /**
     * 删除
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    @Override
    public void delete(int userId, TrainingPlanRequest request) {
        isTeacher(userId);
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        trainingPlanDao.deleteByPrimaryKey(request.getId());
    }

    /**
     * 详细
     *
     * @param request
     * @return
     */
    @Override
    public TrainingPlan detailed(TrainingPlanRequest request) {
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return trainingPlanDao.selectByPrimaryKey(request.getId());
    }

    /**
     * 获取所有的key和value  供sqlect标签使用
     *
     * @return
     */
    @Override
    public PlanViewModel listKey() {
        List<TrainingPlan> trainingPlans = trainingPlanDao.findListKey();
        List<PlanView> list = null;
        if(trainingPlans!=null){
            list = trainingPlans.stream().map(en -> PlanView.builder().id(en.getId()).title(en.getTitle()).build()).collect(Collectors.toList());
        }
        return PlanViewModel.builder().list(list).build();
    }
}
