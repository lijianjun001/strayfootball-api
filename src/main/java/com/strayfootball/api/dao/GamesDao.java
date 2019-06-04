package com.strayfootball.api.dao;

import com.strayfootball.api.entity.Games;
import com.strayfootball.api.entity.UserTrain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Games record);

    int insertSelective(Games record);

    Games selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Games record);

    int updateByPrimaryKey(Games record);

    /**
     * 获取所有比赛信息
     * @return
     */
    List<Games> findList();

    /**
     * 多条件动态查询
     */
    List<Games> findListByDynamic(Games userTrain);
}