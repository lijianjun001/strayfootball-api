package com.strayfootball.api.service.impl;

import com.strayfootball.api.config.RoleType;
import com.strayfootball.api.dao.UserDao;
import com.strayfootball.api.entity.User;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description
 *服务基类
 * @author Karl
 * @create 2019/6/1 17:22
 */
public class BaseService {
    @Autowired
    private  UserDao userDao;


    /**
     * 检查是否是管理员
     * @param userId
     */
    protected void isAdministrator(int userId) {
        User user = userDao.findOneById(userId);
        if (user == null|| StringUtil.isNullOrEmpty(user.getRolesName()) ||!(user.getRolesName().equals(RoleType.ADMIN.getName()))) {
            throw new ApiException("对不起你没有权，只有管理员才能操作！");
        }
    }

    /**
     * 检查是否是教练
     * @param userId
     */
    protected void isTeacher(int userId) {
        User user = userDao.findOneById(userId);
        if (user == null|| StringUtil.isNullOrEmpty(user.getRolesName()) ||!(user.getRolesName().equals(RoleType.TEACHER.getName()))) {
            throw new ApiException("对不起你没有权限，只有教练员才能操作！");
        }
    }
    /**
     * 检查是否是队医
     * @param userId
     */
    protected void isDoctor(int userId) {
        User user = userDao.findOneById(userId);
        if (user == null|| StringUtil.isNullOrEmpty(user.getRolesName()) ||!(user.getRolesName().equals(RoleType.TEAMDOCTOR.getName()))) {
            throw new ApiException("对不起你没有权限，只有队医才能操作！");
        }
    }
}
