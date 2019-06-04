package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * games
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Games {
    private Integer id;

    /**
     * 赛事名称
     */
    private String title;

    /**
     * 比赛甲方
     */
    private Integer partyA;

    /**
     * 比赛乙方
     */
    private Integer partyB;

    /**
     * 获胜方
     */
    private Integer winner;

    /**
     * 状态:“00”未开始,“01”已结束
     */
    private String state;

    /**
     * 创建时间
     */
    private Date created;
    /**
     * 比赛日期
     */
    private Date time;

    /**
     * 比分
     */
    private String score;

    private String a;
    private String b;
    private String win;
}