package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * training_plan
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlan {
    private Integer id;

    /**
     * 训练内容
     */
    private String title;

    /**
     * 训练时间
     */
    private Date time;

    /**
     * 创建日期
     */
    private Date created;

    /**
     * 类型:"00"课训练，“01”周训练，“02”月训练
     */
    private String type;

    /**
     * 是否结束 true 结束，false 未结束
     */
    private Boolean end;
    private String showType;
}