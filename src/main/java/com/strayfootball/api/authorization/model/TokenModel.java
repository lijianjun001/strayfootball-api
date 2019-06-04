package com.strayfootball.api.authorization.model;

import lombok.Data;

/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 * @author karl
 */
@Data
public class TokenModel {

    /**
     * 用户id
     */
    private int userId;

    /**
     * 随机生成的uuid
     */
    private String token;

    public TokenModel(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
