package com.strayfootball.api.dao;

import com.strayfootball.api.entity.MemberToken;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTokenDAO {
    int deleteById(Integer id);

    int insert(MemberToken record);

    int insertSelective(MemberToken record);

    MemberToken findById(Integer id);

    //MemberToken findByMemberId(Integer id);

    MemberToken findFirstByToken(String token);

    MemberToken findFirstByMemberId(Integer memberId);

    int updateByIdSelective(MemberToken record);

    int updateById(MemberToken record);
    int deleteByUserId(Integer userid);

}