package com.strayfootball.api.controller;

import com.strayfootball.api.authorization.context.BaseContextHandler;
import com.strayfootball.api.dto.ResponseModel;
import com.strayfootball.api.dto.request.train.UserTrainRequest;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.UserTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.strayfootball.api.dto.ResponseModel.error;

/**
 * description
 *球员 训练控制器
 * @author Karl
 * @create 2019/6/1 23:20
 */
@RestController
@RequestMapping("/api/userTrain")
public class UserTrainController {
    private final UserTrainService userTrainService;
    @Autowired
    public UserTrainController(UserTrainService userTrainService) {
        this.userTrainService = userTrainService;
    }

    /**
     * 获取所有训练信息
     *
     * @return ok
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel list() {
        try {
            return ResponseModel.ok(userTrainService.list());
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 添加信息
     *
     * @return ok
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel add(@RequestBody UserTrainRequest model) {
        try {
            userTrainService.add(BaseContextHandler.getCustomerId(),model);
            return ResponseModel.ok();
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
    /**
     * 根据条件查询获取所有训练信息
     *
     * @return ok
     */
    @RequestMapping(value = "/listBySelect", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel listBySelect(@RequestBody UserTrainRequest request)  {
        try {
            return ResponseModel.ok(userTrainService.list(request));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }


    /**
     * 根据条件查询获取所球员自己自己有训练信息（仅供自己查看）
     *
     * @return ok
     */
    @RequestMapping(value = "/listByMyTrainSelect", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel listByMyTrainSelect(@RequestBody UserTrainRequest request)  {
        try {
            return ResponseModel.ok(userTrainService.list(BaseContextHandler.getCustomerId(),request));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 编辑信息
     *
     * @return ok
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel edit(@RequestBody UserTrainRequest model) {
        try {
            userTrainService.update(BaseContextHandler.getCustomerId(), model);
            return ResponseModel.ok();
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 删除信息
     *
     * @return ok
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel delete(@RequestBody UserTrainRequest model) {
        try {
            userTrainService.delete(BaseContextHandler.getCustomerId(), model);
            return ResponseModel.ok();
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
    /**
     * 获取详细信息
     *
     * @return ok
     */
    @RequestMapping(value = "/detailed", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel detailed(@RequestBody UserTrainRequest model) {
        try {
            return ResponseModel.ok( userTrainService.detailed(model));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
}
