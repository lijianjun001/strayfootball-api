package com.strayfootball.api.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description
 *登录返回dto
 * @author Karl
 * @create 2019/6/1 20:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginViewModel {
    /**
     * 用户token
     */
    private String   token;
    /**
     * 角色
     */
    private String   role;
    /**
     * 账户
     */
    private String   account;
    /**
     * 名称
     */
    private String   name;

}
