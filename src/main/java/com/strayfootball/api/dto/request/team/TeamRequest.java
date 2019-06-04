package com.strayfootball.api.dto.request.team;

import lombok.Data;

/**
 * description
 *球队能信息数据传输对象
 * @author Karl
 * @create 2019/6/1 16:05
 */
@Data
public class TeamRequest {
    /**
     * 主键
     */
    private int id;
    /**
     * 球队名称
     */
    private String name;

    /**
     * 球队简介
     */
    private String desc;

    /**
     * 人数
     */
    private int number;
}
