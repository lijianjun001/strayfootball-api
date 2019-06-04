package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * member
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    /**
     * 主键id
     */
    private Integer id;

    private Integer level1MemberId;

    private Integer level2MemberId;

    /**
     * 账户
     */
    private String phone;

    /**
     * 用户名称
     */
    private String name;

    /***
     * 职务
     */
    private String job;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date created;
}