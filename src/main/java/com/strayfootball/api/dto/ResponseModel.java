package com.strayfootball.api.dto;
import com.strayfootball.api.config.ResultStatus;
import com.strayfootball.api.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

/**
 * 封装json对象，所有返回结果都使用它
 *
 * @author 孙阿龙
 */
@Slf4j
public class ResponseModel {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public ResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = "";
    }

    public ResponseModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.data = content;
    }

    public ResponseModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = "";
    }

    public ResponseModel(Exception ex) {
        this.code = ResultStatus.FAIL.getCode();
        this.message = ex.getMessage();
        this.data = "";
    }

    public ResponseModel(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = content;
    }

    public static ResponseModel ok(Object content) {
        return new ResponseModel(ResultStatus.SUCCESS, content);
    }

    public static ResponseModel ok() {
        return new ResponseModel(ResultStatus.SUCCESS);
    }

    public static ResponseModel error(ResultStatus error) {
        return new ResponseModel(error);
    }

    public static ResponseModel error(String message) {
        return new ResponseModel(ResultStatus.FAIL.getCode(), message);
    }

    public static ResponseModel error(Exception ex, Class clazz) {
        return new ResponseModel(ResultStatus.FAIL);
    }

    public static ResponseModel error(ApiException error) {
        if (null != error.getStatus()) {
            return new ResponseModel(error.getStatus());
        }
        return new ResponseModel(ResultStatus.FAIL.getCode(), error.getMessage());
    }

    public static ResponseModel error(Exception ex) {
        return new ResponseModel(ex);
    }
}
