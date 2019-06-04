package com.strayfootball.api.dto.request.games;


import lombok.Data;

/**
 * description
 *比请求实体
 * @author Karl
 * @create 2019/6/1 23:52
 */
@Data
public class GamesRequest {
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
     * 比赛日期
     */
    private String time;
    /**
     * 比分
     */
    private String score;
}
