package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * team
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Integer id;

    /**
     * 球队名称
     */
    private String name;

    /**
     * 球队简介
     */
    private String introduction;
    /**
     * 人数
     */
    private int number;
}