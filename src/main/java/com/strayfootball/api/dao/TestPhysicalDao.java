package com.strayfootball.api.dao;

import com.strayfootball.api.entity.TestPhysical;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestPhysicalDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TestPhysical record);

    int insertSelective(TestPhysical record);
    TestPhysical selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(TestPhysical record);

    int updateByPrimaryKey(TestPhysical record);

    /**
     * 获取所有信息
     * @return
     */
    List<TestPhysical> findList();

    /**
     * 根据 球员id查询信息
     * @param userId 球员id
     * @return
     */
    TestPhysical findByUserId(Integer userId);
}