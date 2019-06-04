package com.strayfootball.api.dao;

import com.strayfootball.api.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户仓储
 * @author ID_YiQuan
 */
@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询用户信息  含有球队信息 和 角色
     * @param id
     * @return
     */
    User findOneById(Integer id);

    /**
     * 获取所有用户
     * @return
     */
    List<User>  findListUser();
    /**
     * 获取所有球员 用户ID
     * @return
     */
    List<User>  findListUserByTeamId(Integer teamId);
    /**
     * 通过账户信息查询信息
     * @param account 账户信息
     * @return
     */
   User findOneByAccount(String account);


    /**
     * 获取所有用户 key 和名称
     * @return
     */
    List<User>  findListKey();


    /**多条件动态查询
     *
     * @param request
     * @return
     */
    List<User> findListByDynamic(User request);

    /**
     * 动态获取 所有教练
     * @param request
     * @return
     */
    List<User> findListTeacherByDynamic(User request);

    /**
     * 动态获取 所有球员
     * @param request
     * @return
     */
    List<User> findListPlayerByDynamic(User request);

}