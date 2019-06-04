package com.strayfootball.api.service;

import com.strayfootball.api.dto.request.physical.TestPhysicalRequest;
import com.strayfootball.api.entity.TestPhysical;

import java.util.List;

/**
 * description
 * 球员体测服务
 *
 * @author Karl
 * @create 2019/6/2 9:48
 */
public interface TestPhysicalService {

    /**
     * 获取所有信息
     *
     * @return
     */
    List<TestPhysical> list();

    /**
     * 添加
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    void add(int userId, TestPhysicalRequest request);

    /**
     * 修改
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    void update(int userId, TestPhysicalRequest request);


    /**
     * 删除
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    void delete(int userId, TestPhysicalRequest request);

    /**
     * 详细
     *
     * @param request
     * @return
     */
    TestPhysical detailed(TestPhysicalRequest request);

    /**
     * 获取远动员的体测信息
     *
     * @param userId
     * @return
     */
    TestPhysical myPhysicalInfo(int userId);
}
