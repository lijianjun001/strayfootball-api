package com.strayfootball.api.service.impl;

import com.strayfootball.api.config.RoleType;
import com.strayfootball.api.dao.MemberTokenDAO;
import com.strayfootball.api.dao.UserDao;
import com.strayfootball.api.dto.request.user.UserRequest;
import com.strayfootball.api.dto.response.user.LoginViewModel;
import com.strayfootball.api.dto.response.user.UserView;
import com.strayfootball.api.dto.response.user.UserViewModel;
import com.strayfootball.api.entity.MemberToken;
import com.strayfootball.api.entity.User;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.UserService;
import com.strayfootball.api.util.ApiUtil;
import com.strayfootball.api.util.DateUtil;
import com.strayfootball.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description
 * 用户服务类
 *
 * @author Karl
 * @create 2019/6/1 17:42
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    private final UserDao userDao;
    private final MemberTokenDAO memberTokenDAO;

    @Autowired
    public UserServiceImpl(UserDao userDao, MemberTokenDAO memberTokenDAO) {
        this.userDao = userDao;
        this.memberTokenDAO = memberTokenDAO;
    }

    /**
     * 所有的球员
     *
     * @return
     */
    @Override
    public List<User> list() {
        return userDao.findListUser();
    }

    /**
     * 所有的球员 某个球队的
     *
     * @param request 球队的id
     * @return
     */
    @Override
    public List<User> list(UserRequest request) {
        if (null == request || request.getTeamId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return userDao.findListUserByTeamId(request.getTeamId());
    }

    /**
     * /**添加球员信息（管理员可以添加信息）
     *
     * @param userId  当前用户
     * @param request 请求实体
     */
    @Override
    public void add(int userId, UserRequest request) {
        isAdministrator(userId);
        if (null == request || StringUtil.isNullOrEmpty(request.getName()) || StringUtil.isNullOrEmpty(request.getPassword()) || StringUtil.isNullOrEmpty(request.getAccount())) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        User user = userDao.findOneByAccount(request.getAccount());
        if (user != null) {
            throw new ApiException("账户已经存在，请修改！");
        }
        userDao.insertSelective(User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .rolesId(request.getRolesId())
                .teamId(request.getTeamId())
                .phone(request.getPhone())
                .account(request.getAccount())
                .build());
    }

    /**
     * 添加球员信息（用户自己注册）
     *
     * @param request 请求实体
     */
    @Override
    public void add(UserRequest request) {

        if (null == request || StringUtil.isNullOrEmpty(request.getAccount()) || StringUtil.isNullOrEmpty(request.getPassword())) {
            throw new ApiException("请检查信息是否填写正确！");
        }

        User user = userDao.findOneByAccount(request.getAccount());
        if (user != null) {
            throw new ApiException("账户已经存在，请修改！");
        }

        userDao.insertSelective(User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .rolesId(RoleType.PLAYER.getValue())
                .teamId(request.getTeamId())
                .phone(request.getPhone())
                .account(request.getAccount())
                .build());

    }

    /**
     * 编辑信息
     *
     * @param userId  当前用户
     * @param request 请求实体
     */
    @Override
    public void edit(int userId, UserRequest request) {

        if (null == request || StringUtil.isNullOrEmpty(request.getName()) || StringUtil.isNullOrEmpty(request.getPassword()) || StringUtil.isNullOrEmpty(request.getAccount())) {
            throw new ApiException("请检查信息是否填写正确！");
        }
        User user = userDao.findOneByAccount(request.getAccount());

        //若不是自己修自己的改信息 就得检测是否是管理员
        if (userId != request.getId()) {
            isAdministrator(userId);
            if (user != null&&!(user.getId().equals(request.getId()))) {
                throw new ApiException("账户已经存在，请修改！");
            }
        }else {
            //这一步是自己修改自己的
            if (user != null&&!(user.getId().equals(userId))) {
                throw new ApiException("账户已经存在，请修改！");
            }
        }

        userDao.updateByPrimaryKeySelective(User.builder().id(request.getId())
                .name(request.getName())
                .password(request.getPassword())
                .rolesId(request.getRolesId())
                .teamId(request.getTeamId())
                .phone(request.getPhone())
                .account(request.getAccount())
                .build());
    }

    /**
     * 删除信息
     *
     * @param userId  当前用户
     * @param request 请求实体
     */
    @Override
    public void delete(int userId, UserRequest request) {
        isAdministrator(userId);
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        userDao.deleteByPrimaryKey(request.getId());
    }

    /**
     * 详细
     *
     * @param request
     * @return
     */
    @Override
    public User detailed(UserRequest request) {
        if (null == request || request.getId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return userDao.findOneById(request.getId());
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @Override
    public LoginViewModel login(UserRequest request) {
        if (null == request || StringUtil.isNullOrEmpty(request.getPassword()) || StringUtil.isNullOrEmpty(request.getAccount())) {
            throw new ApiException("账户和密码是否填写正确！");
        }
        User user = userDao.findOneByAccount(request.getAccount());
        if (user == null) {
            throw new ApiException("账户不存在！");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ApiException("密码不正确");
        }
        String token = ApiUtil.token(user.getId().toString());
        Date expireAt = DateUtil.addDay(new Date(), 7);
        MemberToken memberToken = memberTokenDAO.findFirstByMemberId(user.getId());
        if (null == memberToken) {
            memberToken = MemberToken.builder().token(token).expTime(expireAt).memberId(user.getId()).build();
            memberTokenDAO.insert(memberToken);
        } else {
            memberToken.setToken(token);
            memberToken.setExpTime(expireAt);
            memberTokenDAO.updateByIdSelective(memberToken);
        }

        return LoginViewModel.builder().token(token).role(user.getRolesName()).account(user.getAccount()).name(user.getName()).build();
    }

    /**
     * 获取所有的球员供select标签使用
     *
     * @return
     */
    @Override
    public UserViewModel listKey() {
        List<User> users = userDao.findListKey();
        List<UserView> list = null;
        if (users != null) {
            list = users.stream().map(en -> UserView.builder().id(en.getId()).name(en.getName()).build()).collect(Collectors.toList());
        }
        return UserViewModel.builder().list(list).build();
    }

    /**
     * 选着性查询信息
     *
     * @param request 请求实体
     * @return
     */
    @Override
    public List<User> listBySelect(UserRequest request) {
        if (null == request) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return userDao.findListByDynamic(User.builder().account(request.getAccount()).phone(request.getPhone()).teamId(request.getTeamId()).name(request.getName()).build());
    }

    /**
     * 所有的教练
     *
     * @param request
     * @return
     */
    @Override
    public List<User> listTeacher(UserRequest request) {
        if (null == request) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return userDao.findListTeacherByDynamic(User.builder().account(request.getAccount()).phone(request.getPhone()).teamId(request.getTeamId()).name(request.getName()).build());
    }

    /**
     * 获取自己的信息
     *
     * @param userId
     * @return
     */
    @Override
    public User myInfo(int userId) {
        return userDao.findOneById(userId);
    }

    /**
     * 所有的教练
     *
     * @param request
     * @return
     */
    @Override
    public List<User> listPlayer(UserRequest request) {
        if (null == request) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        return userDao.findListPlayerByDynamic(User.builder().account(request.getAccount()).phone(request.getPhone()).teamId(request.getTeamId()).name(request.getName()).build());
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @Override
    public void signOut(int userId) {
        memberTokenDAO.deleteByUserId(userId);
    }


}
