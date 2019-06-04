package com.strayfootball.api.service.impl;

import com.strayfootball.api.dao.UserDao;
import com.strayfootball.api.dao.UserTrainDao;
import com.strayfootball.api.dto.request.train.UserTrainRequest;
import com.strayfootball.api.entity.User;
import com.strayfootball.api.entity.UserTrain;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.UserTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 * 训练记录服务
 *
 * @author Karl
 * @create 2019/6/1 22:22
 */
@Service
public class UserTrainServoceImpl extends BaseService implements UserTrainService {
    private final UserTrainDao userTrainDao;
    private final UserDao userDao;

    @Autowired
    public UserTrainServoceImpl(UserTrainDao userTrainDao, UserDao userDao) {
        this.userTrainDao = userTrainDao;
        this.userDao = userDao;
    }

    /**
     * 获取所有球员训练信息
     *
     * @return
     */
    @Override
    public List<UserTrain> list() {
        return userTrainDao.findList();
    }

    /**
     * 取该该训练所有球员训练信息
     *
     * @param request 请求实体
     * @return
     */
    @Override
    public List<UserTrain> list(UserTrainRequest request) {

        if (null == request) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return userTrainDao.selectUserByDynamic(UserTrain.builder().marks(request.getMarks()).trainingPlanId(request.getTrainingPlanId()).userId(request.getUserId()).build());
        //userTrainDao.findListByTainId(request.getTrainingPlanId());
    }

    /**
     * 添加训练成绩信息
     *
     * @param userId  教练员id
     * @param request 请求实体
     * @return
     */
    @Override
    public void add(int userId, UserTrainRequest request) {
        isTeacher(userId);
        if (null == request || request.getTrainingPlanId() <= 0 || request.getUserId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        User user = userDao.selectByPrimaryKey(request.getUserId());
        if (user == null) {
            throw new ApiException("球员信息不存在！");
        }
        if (userTrainDao.findByPlanIdAndUserId(UserTrain.builder().trainingPlanId(request.getTrainingPlanId()).userId(request.getUserId()).build()) != null) {
            throw new ApiException("该球员本次计划已经录入训练成绩！请勿重复录入。");
        }
        userTrainDao.insertSelective(UserTrain.builder()
                .created(new Date())
                .marks(request.getMarks())
                .userId(request.getUserId())
                .trainingPlanId(request.getTrainingPlanId())
                .build());
    }

    /**
     * 修改
     *
     * @param userId  教练员id
     * @param request 请求实体
     * @return
     */
    @Override
    public void update(int userId, UserTrainRequest request) {
        isTeacher(userId);
        if (null == request || request.getTrainingPlanId() <= 0 || request.getUserId() <= 0 || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }

        UserTrain userTrain = userTrainDao.findByPlanIdAndUserId(UserTrain.builder().trainingPlanId(request.getTrainingPlanId()).userId(request.getUserId()).build());

        if (userTrain != null && !userTrain.getId().equals(request.getId())) {
            throw new ApiException("该球员本次计划已经录入训练成绩！请勿重复录入。");
        }

        userTrainDao.updateByPrimaryKeySelective(UserTrain.builder().id(request.getId())
                .created(new Date())
                .marks(request.getMarks())
                .userId(request.getUserId())
                .trainingPlanId(request.getTrainingPlanId())
                .build());
    }

    /**
     * 删除信息
     *
     * @param userId  教练员id
     * @param request 请求实体
     * @return
     */
    @Override
    public void delete(int userId, UserTrainRequest request) {
        isTeacher(userId);
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        userTrainDao.deleteByPrimaryKey(request.getId());
    }

    /**
     * 详细
     *
     * @param request
     * @return
     */
    @Override
    public UserTrain detailed(UserTrainRequest request) {

        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        UserTrain userTrain = userTrainDao.selectByPrimaryKey(request.getId());
        if (null == userTrain) {
            throw new ApiException("没有查询到数据！");
        }
        return UserTrain.builder().id(userTrain.getId())
                .created(userTrain.getCreated())
                .marks(userTrain.getMarks())
                .userId(userTrain.getUserId())
                .trainingPlanId(userTrain.getTrainingPlanId())
                .name(userTrain.getName())
                .title(userTrain.getTitle())
                .type(userTrain.getType())
                .build();
    }

    /**
     * 获取该该训练所有球员训练信息(仅供运动员查看自己的信息)
     *
     * @param userId
     * @param request 请求实体
     * @return
     */
    @Override
    public List<UserTrain> list(int userId, UserTrainRequest request) {
        if (null == request) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return userTrainDao.selectUserByDynamic(UserTrain.builder().marks(request.getMarks()).trainingPlanId(request.getTrainingPlanId()).userId(userId).build());
    }


}
