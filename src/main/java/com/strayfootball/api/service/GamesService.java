package com.strayfootball.api.service;

import com.strayfootball.api.dto.request.games.GamesRequest;
import com.strayfootball.api.entity.Games;

import java.util.List;

/**
 * description
 *比赛服务层
 * @author Karl
 * @create 2019/6/1 23:50
 */
public interface GamesService {


    /**
     * 根据条件查询
     * @param request
     * @return
     */
    List<Games> list(GamesRequest request);

    /**
     * 添加
     * @param userId 管理员id
     * @param request 请求实体
     * @return
     */
    void add(int userId, GamesRequest request);


    /**修改
     * @param userId 管理员id
     * @param request 请求实体
     * @return
     */
    void update(int userId,GamesRequest request);


    /**
     * 删除
     * @param userId 管理员id
     * @param request 请求实体
     * @return
     */
    void delete(int userId,GamesRequest request);

    /**
     * 详细
     * @param request
     * @return
     */
    Games  detailed(GamesRequest request);
}
