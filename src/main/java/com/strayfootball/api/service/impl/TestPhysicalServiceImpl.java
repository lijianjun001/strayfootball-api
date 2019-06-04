package com.strayfootball.api.service.impl;

import com.strayfootball.api.dao.TestPhysicalDao;
import com.strayfootball.api.dao.UserDao;
import com.strayfootball.api.dto.request.physical.TestPhysicalRequest;
import com.strayfootball.api.entity.TestPhysical;
import com.strayfootball.api.entity.User;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.TestPhysicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description
 *体测服务
 * @author Karl
 * @create 2019/6/2 9:55
 */
@Service
public class TestPhysicalServiceImpl extends BaseService implements TestPhysicalService {

    private final UserDao userDao;
    private final TestPhysicalDao testPhysicalDao;

    @Autowired
    public TestPhysicalServiceImpl(UserDao userDao, TestPhysicalDao testPhysicalDao) {
        this.userDao = userDao;
        this.testPhysicalDao = testPhysicalDao;
    }


    /**
     * 获取所有信息
     *
     * @return
     */
    @Override
    public List<TestPhysical> list() {
        return testPhysicalDao.findList();
    }

    /**
     * 添加
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    @Override
    public void add(int userId, TestPhysicalRequest request) {
        isDoctor(userId);
        if (null == request || request.getUserId() <= 0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        User user = userDao.selectByPrimaryKey(request.getUserId());
        if (user == null) {
            throw new ApiException("球员信息不存在！");
        }
        if(testPhysicalDao.findByUserId(request.getUserId())!=null){
            throw new ApiException("该球员已经录入了身体体测数据！请勿重复录入！");
        }

        testPhysicalDao.insertSelective(TestPhysical.builder()
                .armExhibition(request.getArmExhibition())
                .bloodPressure(request.getBloodPressure())
                .bodyFat(request.getBodyFat())
                .endurance(request.getEndurance())
                .height(request.getHeight())
                .weight(request.getWeight())
                .userId(request.getUserId())
                .vitalCapacity(request.getVitalCapacity())
                .metersDashTime(request.getMetersDashTime())
                .bodyCondition(request.getBodyCondition())
                .build());
    }

    /**
     * 修改
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    @Override
    public void update(int userId, TestPhysicalRequest request) {
        isDoctor(userId);
        if (null == request || request.getUserId() <= 0||request.getId()<=0) {
            throw new ApiException("请检查信息是否传递正确！");
        }

        User user = userDao.selectByPrimaryKey(request.getUserId());
        if (user == null) {
            throw new ApiException("球员信息不存在！");
        }
        TestPhysical testPhysical=testPhysicalDao.findByUserId(request.getUserId());

        if(testPhysical!=null&&!(testPhysical.getId().equals(request.getId()))){
            throw new ApiException("该球员已经录入了身体体测数据！请勿重复录入！");
        }
        testPhysicalDao.updateByPrimaryKeySelective(TestPhysical.builder().id(request.getId())
                .armExhibition(request.getArmExhibition())
                .bloodPressure(request.getBloodPressure())
                .bodyFat(request.getBodyFat())
                .endurance(request.getEndurance())
                .height(request.getHeight())
                .weight(request.getWeight())
                .userId(request.getUserId())
                .vitalCapacity(request.getVitalCapacity())
                .bodyCondition(request.getBodyCondition())
                .metersDashTime(request.getMetersDashTime())
                .build());
    }

    /**
     * 删除
     *
     * @param userId  队医id
     * @param request 请求实体
     * @return
     */
    @Override
    public void delete(int userId, TestPhysicalRequest request) {
        isDoctor(userId);
        if (null == request ||request.getId()<=0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
        testPhysicalDao.deleteByPrimaryKey(request.getId());
    }

    /**
     * 详细
     *
     * @param request
     * @return
     */
    @Override
    public TestPhysical detailed(TestPhysicalRequest request) {
        if (null == request ||request.getId()<=0) {
            throw new ApiException("请检查信息是否传递正确！");
        }
       return testPhysicalDao.selectByPrimaryKey(request.getId());
    }

    /**
     * 获取远动员的体测信息
     *
     * @param userId
     * @return
     */
    @Override
    public TestPhysical myPhysicalInfo(int userId) {

        TestPhysical testPhysical=testPhysicalDao.findByUserId(userId);
        if (testPhysical==null){
            throw new ApiException("你还没有体测，请找队医录入体测信息！");
        }
        return    testPhysical;
    }
}
