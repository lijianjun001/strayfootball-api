package com.strayfootball.api.service.impl;

import com.strayfootball.api.dao.GamesDao;
import com.strayfootball.api.dao.TeamDao;
import com.strayfootball.api.dto.request.games.GamesRequest;
import com.strayfootball.api.entity.Games;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.GamesService;
import com.strayfootball.api.util.DateUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * description
 * 比赛服务类实现
 *
 * @author Karl
 * @create 2019/6/1 23:55
 */
@Service
public class GamesServiceImpl extends BaseService implements GamesService {
    private final GamesDao gamesDao;
    private final TeamDao teamDao;

    @Autowired
    public GamesServiceImpl(GamesDao gamesDao, TeamDao teamDao) {
        this.gamesDao = gamesDao;
        this.teamDao = teamDao;
    }


    /**
     * 根据条件查询
     *
     * @param request
     * @return
     */
    @Override
    public List<Games> list(GamesRequest request) {

        if (null == request) {
            throw new ApiException("请检查信息是否传递正确！");
        }
            return gamesDao.findListByDynamic(Games.builder().state(request.getState()).winner(request.getWinner()).title(request.getTitle()).build());

    }

    /**
     * 添加
     *
     * @param userId  管理员id
     * @param request 请求实体
     * @return
     */
    @Override
    public void add(int userId, GamesRequest request) {
        isAdministrator(userId);
        if (null == request || StringUtil.isNullOrEmpty(request.getTitle()) || request.getPartyA() <= 0 || request.getPartyB() <= 0 || StringUtil.isNullOrEmpty(request.getTime())) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        if (request.getPartyA().equals(request.getPartyB())) {
            throw new ApiException("比赛双方不能相同！");
        }
        if(request.getWinner()>0){
            if (!request.getPartyA().equals(request.getWinner())&&!request.getPartyB().equals(request.getWinner())){

                throw new ApiException("获胜方必须是这两方之一！");
            }
        }
        if (teamDao.selectByPrimaryKey(request.getPartyA()) == null || teamDao.selectByPrimaryKey(request.getPartyB()) == null) {
            throw new ApiException("球队信息没有查到，请检查球队是否选错！");
        }
        gamesDao.insertSelective(Games.builder()
                .partyA(request.getPartyA())
                .partyB(request.getPartyB())
                .score(request.getScore())
                //yyyy-MM-dd HH:mm:ss
                .time(DateUtil.strToDate(request.getTime()))
                .title(request.getTitle())
                .winner(request.getWinner())
                .state(request.getState())
                .created(new Date())
                .build());

    }

    /**
     * 修改
     *
     * @param userId  管理员id
     * @param request 请求实体
     * @return
     */
    @Override
    public void update(int userId, GamesRequest request) {
        isAdministrator(userId);
        if (null == request || StringUtil.isNullOrEmpty(request.getTitle()) || request.getPartyA() <= 0 || request.getPartyB() <= 0 || StringUtil.isNullOrEmpty(request.getTime()) || request.getId() <= 0) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        if (request.getPartyA().equals(request.getPartyB())) {
            throw new ApiException("比赛双方不能相同！");
        }
        if(request.getWinner()>0){
            if (!request.getPartyA().equals(request.getWinner())&&!request.getPartyB().equals(request.getWinner())){

                throw new ApiException("获胜方必须是这两方之一！");
            }
        }


        if (teamDao.selectByPrimaryKey(request.getPartyA()) == null || teamDao.selectByPrimaryKey(request.getPartyB()) == null) {
            throw new ApiException("球队信息没有查到，请检查球队是否选错！");
        }
        gamesDao.updateByPrimaryKeySelective(Games.builder().id(request.getId())
                .partyA(request.getPartyA())
                .partyB(request.getPartyB())
                .score(request.getScore())
                //yyyy-MM-dd HH:mm:ss
                .time(DateUtil.strToDate(request.getTime()))
                .title(request.getTitle())
                .winner(request.getWinner())
                .state(request.getState())
                .created(new Date())
                .build());
    }

    /**
     * 删除
     *
     * @param userId  管理员id
     * @param request 请求实体
     * @return
     */
    @Override
    public void delete(int userId, GamesRequest request) {
        isAdministrator(userId);
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        gamesDao.deleteByPrimaryKey(request.getId());
    }

    /**
     * 详细
     *
     * @param request
     * @return
     */
    @Override
    public Games detailed(GamesRequest request) {
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        return gamesDao.selectByPrimaryKey(request.getId());
    }
}
