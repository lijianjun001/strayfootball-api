package com.strayfootball.api.dto.request.physical;

import lombok.Data;


/**
 * 体测信息
 * test_physical
 * @author 
 */
@Data
public class TestPhysicalRequest {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 血压
     */
    private String bloodPressure;

    /**
     * 肺活量
     */
    private String vitalCapacity;

    /**
     * 耐力
     */
    private String endurance;

    /**
     * 体脂
     */
    private String bodyFat;

    /**
     * 100米短跑所需时间
     */
    private String metersDashTime;

    /**
     * 臂展
     */
    private String armExhibition;

    /**
     * 用户ID
     */
    private Integer userId;
    /**
     *身体状况“00” 亚健康，"01"良好，02 优
     */
    private String bodyCondition;
}