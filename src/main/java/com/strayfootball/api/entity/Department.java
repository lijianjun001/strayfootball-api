package com.strayfootball.api.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * department
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    /**
     * 主键
     */
    private Integer id;

    private Integer leaderMemberId;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级id
     */
    private Integer parentid;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date created;


}