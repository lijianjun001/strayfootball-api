package com.strayfootball.api.authorization.manager.impl;

import com.strayfootball.api.entity.MemberToken;
import com.strayfootball.api.util.StringUtil;
import com.strayfootball.api.authorization.manager.TokenManager;
import com.strayfootball.api.authorization.model.TokenModel;
import com.strayfootball.api.dao.MemberTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通过Redis存储和验证token的实现类
 *
 * @author karl
 */
@Component
public class TokenManagerImpl implements TokenManager {

    private final MemberTokenDAO memberTokenDAO;

    @Autowired
    public TokenManagerImpl(MemberTokenDAO memberTokenDAO) {
        this.memberTokenDAO = memberTokenDAO;
    }


    @Override
    public TokenModel createToken(int customerId, String platform) throws Exception {
        return null;
    }

    @Override
    public boolean checkToken(TokenModel model) {

        return false;
    }

    @Override
    public TokenModel getToken(String authentication, String platform) {

        try {
            if (StringUtil.isNullOrEmpty(authentication)) {
                return null;
            }

            MemberToken memberToken = memberTokenDAO.findFirstByToken(authentication);
            if (memberToken == null) {
                return null;
            }

            if (memberToken.getExpTime().getTime() < System.currentTimeMillis()) {
                return null;
            }

            return new TokenModel(memberToken.getMemberId(), authentication);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void deleteToken(int userId) {

    }
}
