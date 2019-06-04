package com.strayfootball.api.exception;
import com.strayfootball.api.authorization.constatns.CommonConstants;

/**
 * 客户token异常
 * @author 孙阿龙
 */
public class CustomerTokenException extends BaseException {
    public CustomerTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
