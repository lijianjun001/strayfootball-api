package com.strayfootball.api.service;

import com.strayfootball.api.dto.request.user.UserRequest;
import com.strayfootball.api.dto.response.user.LoginViewModel;
import com.strayfootball.api.dto.response.user.UserViewModel;
import com.strayfootball.api.entity.User;

import java.util.List;

/**
 * description
 *用户服务
 * @author Karl
 * @create 2019/6/1 15:36
 */
public interface UserService {
    /**
     * 所有的球员
     * @return
     */
    List<User> list();

    /**
     * 所有的球员 某个球队的
     * @param request 球队的id
     * @return
     */
    List<User> list( UserRequest request);

    /**添加球员信息（管理员可以添加信息）
     * @param userId 当前用户
     * @param request 请求实体
     */
    void add(int userId, UserRequest request);

    /**添加球员信息（用户自己注册）
     * @param request 请求实体
     */
    void add(UserRequest request);
    /**
     *编辑信息
     * @param userId 当前用户
     * @param request 请求实体
     */
    void edit(int userId,UserRequest request);
    /**
     * 删除信息
     * @param userId 当前用户
     * @param request 请求实体
     */
    void delete(int userId,UserRequest request);

    /**
     * 详细
     * @param request
     * @return
     */
    User  detailed(UserRequest request);

    /**
     * 用户登录
     * @param request
     * @return
     */
    LoginViewModel login(UserRequest request);


    /**
     * 获取所有的球员供select标签使用
     * @return
     */
    UserViewModel listKey();

    /**
     * 选着性查询信息
     * @param request 请求实体
     * @return
     */
    List<User> listBySelect(UserRequest request);
    /**
     * 所有的教练
     * @param request
     * @return
     */
    List<User> listTeacher( UserRequest request);

    /**
     * 获取自己的信息
     * @param userId
     * @return
     */
    User  myInfo(int userId);

    /**
     * 所有的教练
     * @param request
     * @return
     */
    List<User> listPlayer( UserRequest request);

    /**
     * 退出登录
     * @param userId
     * @return
     */
    void  signOut(int userId);
}
