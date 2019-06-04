package com.strayfootball.api.configuration;

import com.strayfootball.api.config.ResultStatus;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.dto.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.strayfootball.api.dto.ResponseModel.error;


/**
 * 统一过滤异常
 *
 * @author karl
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseModel handleException(ApiException e) {
        return error(e.getStatus());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseModel handleException(Exception e) {
        return error(ResultStatus.FAIL);
    }
}
