package com.strayfootball.api.dto.request.user;

import lombok.Data;

/**
 * description
 *用户请求实体
 * @author Karl
 * @create 2019/6/1 17:58
 */
@Data
public class UserRequest {
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 角色id
     */
    private Integer rolesId;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 账户
     */
    private String account;

    /**
     * 球队id
     */
    private Integer teamId;

}
