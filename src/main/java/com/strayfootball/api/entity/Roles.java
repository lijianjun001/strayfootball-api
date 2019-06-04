package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * roles
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;
}