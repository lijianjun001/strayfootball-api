package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * user
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
     * 球队id
     */
    private Integer teamId;
    /**
     * 账户
     */
    private String account;

    /**
     * 角色
     */
    private String rolesName;
    /**
     * 球队名称
     */
    private String teamName;

}