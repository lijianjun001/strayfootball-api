package com.strayfootball.api.service;
import com.strayfootball.api.dto.request.train.UserTrainRequest;
import com.strayfootball.api.entity.UserTrain;
import java.util.List;

/**
 * description
 * 球员训练信息服务
 *
 * @author Karl
 * @create 2019/6/1 21:16
 */
public interface UserTrainService {

    /**
     * 获取所有球员训练信息
     *
     * @return
     */
    List<UserTrain> list();


    /**
     * 获取该该训练所有球员训练信息
     *
     * @param request 请求实体
     * @return
     */
    List<UserTrain> list(UserTrainRequest request);

    /**
     * 添加
     * @param userId 教练员id
     * @param request 请求实体
     * @return
     */
    void add(int userId,UserTrainRequest request);


    /**修改
     * @param userId 教练员id
     * @param request 请求实体
     * @return
     */
    void update(int userId,UserTrainRequest request);


    /**
     * 删除
     * @param userId 教练员id
     * @param request 请求实体
     * @return
     */
    void delete(int userId,UserTrainRequest request);

    /**
     * 详细
     * @param request
     * @return
     */
    UserTrain  detailed(UserTrainRequest request);


    /**
     * 获取该该训练所有球员训练信息(仅供运动员查看自己的信息)
     * @param userId
     * @param request
     * @return
     */
    List<UserTrain> list(int userId,UserTrainRequest request);
}
