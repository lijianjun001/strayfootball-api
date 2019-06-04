package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户训练记录
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTrain {
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

    /**
     * 创建日期
     */
    private Date created;
    /**
     * 球员姓名
     */
    private String name;
    /**
     * 训练项目
     */
    private String title;
    /**
     *周训练、月训练、课训练
     */
    private String type;
}