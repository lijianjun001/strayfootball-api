package com.strayfootball.api.service.impl;

import com.strayfootball.api.dao.TeamDao;
import com.strayfootball.api.dto.request.team.TeamRequest;
import com.strayfootball.api.dto.response.team.TeamView;
import com.strayfootball.api.dto.response.team.TeamViewModel;
import com.strayfootball.api.entity.Team;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.TeamService;
import com.strayfootball.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description
 * 球队实现服务类
 *
 * @author Karl
 * @create 2019/6/1 16:10
 */
@Service
public class TeamServiceImpl extends BaseService implements TeamService {

    private final TeamDao teamDao;

    @Autowired
    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    /**
     * 所有的球队
     *
     * @return
     */
    @Override
    public List<Team> list() {
        return teamDao.findList();
    }

    /**
     * 添加球队信息
     *
     * @param userId  当前用户
     * @param request 请求实体
     */
    @Override
    public void add(int userId, TeamRequest request) {
        isAdministrator(userId);
        if (null == request || StringUtil.isNullOrEmpty(request.getName()) || StringUtil.isNullOrEmpty(request.getDesc())) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        teamDao.insertSelective(Team.builder().introduction(request.getDesc()).name(request.getName()).number(request.getNumber()).build());
    }

    /**
     * 编辑球队信息
     *
     * @param userId  当前用户
     * @param request 请求实体
     */
    @Override
    public void edit(int userId, TeamRequest request) {
        isAdministrator(userId);
        if (null == request || StringUtil.isNullOrEmpty(request.getName()) || StringUtil.isNullOrEmpty(request.getDesc()) || request.getId() <= 0) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        teamDao.updateByPrimaryKeySelective(Team.builder().id(request.getId()).introduction(request.getDesc()).name(request.getName()).number(request.getNumber()).build());
    }

    /**
     * 删除信息
     *
     * @param userId  当前用户
     * @param request 请求实体
     */
    @Override
    public void delete(int userId, TeamRequest request) {
        isAdministrator(userId);
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        teamDao.deleteByPrimaryKey(request.getId());
    }

    /**
     * 详细
     *
     * @param request
     * @return
     */
    @Override
    public Team detailed(TeamRequest request) {
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return teamDao.selectByPrimaryKey(request.getId());
    }

    /**
     * 获取所有的球队供select标签使用
     *
     * @return
     */
    @Override
    public TeamViewModel listKey() {
        List<Team> teams = teamDao.findList();
        List<TeamView> list = null;
        if(teams!=null){
            list = teams.stream().map(en -> TeamView.builder().id(en.getId()).name(en.getName()).build()).collect(Collectors.toList());
        }
        return TeamViewModel.builder().list(list).build();
    }
}
