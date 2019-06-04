package com.strayfootball.api.authorization.interceptor;

import com.strayfootball.api.authorization.context.BaseContextHandler;
import com.strayfootball.api.authorization.manager.TokenManager;
import com.strayfootball.api.authorization.model.TokenModel;
import com.strayfootball.api.config.Constants;
import com.strayfootball.api.config.ResultStatus;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.authorization.annotation.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 *
 * @author karl
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String authorization = request.getHeader(Constants.AUTHORIZATION);
        String platform = request.getHeader(Constants.PLATFORM);
        BaseContextHandler.setPlatform(platform);
        //验证token
        TokenModel model = manager.getToken(authorization, platform);
        if (model != null) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            BaseContextHandler.setCustomerId(model.getUserId());
            return super.preHandle(request, response, handler);
        }
        //如果验证token失败，并且方法注明了Authorization，
        if (method.getAnnotation(Authorization.class) != null) {
            if (method.getAnnotation(Authorization.class).onlyGetCustomer()) {
                return super.preHandle(request, response, handler);
            }
            throw new ApiException(ResultStatus.TOKEN_EXP);
        }
        throw new ApiException(ResultStatus.TOKEN_EXP);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
