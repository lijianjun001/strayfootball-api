package com.strayfootball.api.config;

/**
 * 返回结果状态
 *
 * @author karl
 */
public enum ResultStatus {
    PASSWORD_ERROR(-1001, "密码错误"),
    CODE_OR_PASSWORD_ERROR(-1003, "验证码或者密码错误"),
    TOKEN_EXP(-401,"请重新登录"),
    SUCCESS(100, "成功"),
    FAIL(-1, "服务器繁忙，请稍候再试"),
    PARAMETER_MISSING(-2, "参数不完整"),
    PARAMETER_ERROR(-3, "参数错误"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_FOUND(-1002, "用户不存在"),
    USER_NOT_LOGIN(-1003, "用户未登录"),
    DELAYED_CARD_NOT_EXIT(-1004, "延时卡不存在"),
    RED_PACKAGE_NOT_EXIT(-1005, "红包不存在"),
    MERGE_CARD_NOT_EXIT(-1006, "合并卡不存在"),
    SECRET_GIFT_NOT_EXIT(-1007, "神秘礼物不存在"),
    TELEPHONE_CARD_NOT_EXIT(-1008, "充值卡不存在或已使用"),
    MAX_MONTH_SHAY(-1009, "本月分享达到最大分享数,请下月再来"),
    DEVICE_OFFLINE(-1010, "设备已离线"),
    USER_EXISTS_ERROR(-1011, "用户已存在"),
    USER_PASSWORD_LENGTH_ERROR(-1012, "密码长度应设置在6-16位之间"),
    ACCOUNT_EMPTY_ERROR(-1013, "账号不能为空"),
    PASSWORD_EMPTY_ERROR(-1014, "密码不能为空"),
    DATA_QUERY_FAILED(-2000, "未查到该数据"),
    YUNYING_PHONE_ERROR(-6, "由于运营商维护系统将于2018年1月1日恢复充值"),
    TOUTIAO_CALL_FAILED(-3001, "回传失败"),
    PARAMETER_CODE_ERROR(-4001, "验证码错误"),
    PARAMETER_CODE_INVALID(-4002, "验证码已失效"),
    REDEEM_PASSWORD_ERROR(-4003, "赎回密码错误"),
    INVALID_PHONE_ERROR(-4004, "不是有效的手机号"),
    INVALID_RECOMMEND_ERROR(-4005, "推荐人不存在"),
    INVALID_RECOMMEND_CODE_ERROR(-4006, "推荐码不能为空"),
    INVALID_NEWSDETAIL_CODE_ERROR(-4007, "消息不存在"),
    INVALID_REC_ENVELOPE_ERROR(-4008, "该红包不存在"),
    OVERTOP_MAX_ERROR(-4009, "该红包已满员"),
    NOW_COMPLETED_ERROR(-4010, "该红包未满员"),
    LOGIN_NO_REGISTER(-5000, "用户没有注册"),
    LOGIN_INVALID_PWD(-5001, "密码不正确"),
    REDENVELOPE_NOT_EXIT(-6001, "分享红包不存在"),
    REDENVELOPE_YICANYU(-6002, "已经参与过"),
    REDENVELOPE_NO_NEW_CUSTOMER(-6003, "不是新用户"),
    ZMB_NOT_ENOUGH(-1001, "众牧币不足"),
    CUSTOMER_ADDRESS_NOT_EXIT(-2001, "地址信息不存在"),
    PRODUCT_NOT_EXIT(-2002, "商品不存在"),
    INVITECODE_EMPTY_ERROR(-2003,"邀请码不能为空"),
    INVITECODE_NOT_EXIT(-2004,"邀请码不存在"),
    RECEIVER_EMPTY_ERROR(-2005,"收货人不能为空"),
    PHONE_EMPTY_ERROR(-2006,"手机号不能为空"),
    DISTRICT_EMPTY_ERROR(2-007,"地区不能为空"),
    ADDRESS_EMPTY_ERROR(-2008,"详细地址不能为空"),
    REDEEM_PASSWORD_EMPTY(-2009, "未设置赎回密码"),
    QUESTION_EMPTY(-2010, "提问内容为空"),
    NICKNAME_ERROR(-2011, "昵称不符合要求"),
    NICKNAME_EMPTY(-2012, "昵称不能为空"),
    SINA_REQUEST_FAIL(-2013, "新浪请求失败"),
    INIT_SIN_CUSTOMER_ACCOUNT_FAIL(-2014, "初始化新浪用户异常"),
    CUSTOMER_SINA_NO_BIND_CARF(-2015,"没有绑卡，请绑卡"),
    WEEK_OPEND(-2016, "本周已经开过宝箱"),
    MONTH_OPEND(-2017, "本月已经使用过红包特权"),
    NO_RECORD(-2018, "没有查询到你的相关信息"),
    NORECORD_ERROR(-2019, "基本数据查询无效"),
    NOREALNAME_ERROR(-2020, "未实名"),
    ALREADY_INVITE(-2021, "已经有邀请人"),
    PERSONAL_INVITE(-2022, "不能是本人"),
    PROVINCES_EMPTY(-2023, "省份查无数据"),
    CITIES_EMPTY(-2024, "市区查无数据"),
    COUNTIES_EMPTY(-2025, "县城查无数据"),
    INFORMATION_ALREADY_EXISTS(-2026, "已经做出评价，请勿在此提交"),
    HAVE_DATA(-2027, "数据已经录入，请勿重复提交"),
    NO_AUTHORITY(-2028, "没有评分权限"),
    IS_LEADER(-2029, "被评价人是领导")
    ;
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
