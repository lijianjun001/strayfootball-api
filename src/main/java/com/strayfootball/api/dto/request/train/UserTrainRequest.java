package com.strayfootball.api.dto.request.train;

import lombok.Data;

/**
 * description
 *用户训练成绩 记录请求
 * @author Karl
 * @create 2019/6/1 21:52
 */
@Data
public class UserTrainRequest {
    private Integer id;

    /**
     * 训练项目id
     */
    private Integer trainingPlanId;

    /**
     * 球员id
     */
    private int userId;

    /**
     * 得分
     */
    private Double marks;

}
