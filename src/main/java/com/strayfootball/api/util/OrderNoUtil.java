package com.strayfootball.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description
 *订单编号生成 工具类
 * @author Karl
 * @create 2019/3/8 17:22
 */
public class OrderNoUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 生成订单号
     *
     * @param pre 订单号前缀
     * @return
     */
    public static String orderNos(String pre, Long museId) {
        //生成
        String orderNo = pre + sdf.format(new Date()) + (1 + (int) (Math.random() * 10000)) + museId;
        return orderNo;
    }

    /**
     * 生成订单号
     *
     * @param pre 订单号前缀
     * @return
     */
    public static String orderNo(String pre, Long museId) {
        //生成
        String orderNo = pre + ((int)((Math.random()*9+1)*10000)) + museId + (System.currentTimeMillis() / 1000);
        return orderNo;
    }

}
