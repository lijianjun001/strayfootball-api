package com.strayfootball.api.dto.request.plan;

import lombok.Data;
/**
 * 训练 计划dto
 * training_plan
 * @author 
 */
@Data
public class TrainingPlanRequest {
    private Integer id;

    /**
     * 训练内容
     */
    private String title;

    /**
     * 训练时间
     */
    private String time;



    /**
     * 类型:"00"课训练，“01”周训练，“02”月训练
     */
    private String type;

    /**
     * 是否结束 true 结束，false 未结束
     */
    private Boolean end;
}