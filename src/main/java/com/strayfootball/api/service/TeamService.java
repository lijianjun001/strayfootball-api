package com.strayfootball.api.service;

import com.strayfootball.api.dto.request.team.TeamRequest;
import com.strayfootball.api.dto.response.team.TeamViewModel;
import com.strayfootball.api.entity.Team;

import java.util.List;

/**
 * description
 *球队管理服务
 * @author Karl
 * @create 2019/6/1 15:56
 */
public interface TeamService {
    /**
     * 所有的球队
     * @return
     */
    List<Team>  list();


    /**添加球队信心
     * @param userId 当前用户
     * @param request 请求实体
     */
    void add(int userId,TeamRequest request);

    /**
     * 编辑球队信息
     * @param userId 当前用户
     * @param request 请求实体
     */
    void edit(int userId,TeamRequest request);
    /**
     * 删除信息
     * @param userId 当前用户
     * @param request 请求实体
     */
    void delete(int userId,TeamRequest request);

    /**
     * 详细
     * @param request
     * @return
     */
    Team  detailed(TeamRequest request);

    /**
     * 获取所有的球队供select标签使用
     * @return
     */
    TeamViewModel listKey();
}
