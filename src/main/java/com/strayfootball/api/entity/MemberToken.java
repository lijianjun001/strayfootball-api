package com.strayfootball.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * member_token
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberToken implements Serializable {
    private Integer id;

    private Integer memberId;

    private String token;

    private Date expTime;

    private static final long serialVersionUID = 1L;

}