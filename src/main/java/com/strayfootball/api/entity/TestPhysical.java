package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 体测信息
 * test_physical
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestPhysical  {
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
     * 球员id
     */
    private Integer userId;
    /**
     *球员姓名
     */
    private String name;
    /**
     *身体状况“00” 亚健康，"01"良好，02 优
     */
    private String bodyCondition;
}