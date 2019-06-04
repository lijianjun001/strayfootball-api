package com.strayfootball.api.controller;

import com.strayfootball.api.authorization.annotation.Authorization;
import com.strayfootball.api.authorization.context.BaseContextHandler;
import com.strayfootball.api.dto.ResponseModel;
import com.strayfootball.api.dto.request.user.UserRequest;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.strayfootball.api.dto.ResponseModel.error;

/**
 * description
 * 球员控制器
 *
 * @author Karl
 * @create 2019/6/1 18:27
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取所有球球员信息
     *
     * @return ok
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseModel list(@RequestBody UserRequest request) {
        try {
            return ResponseModel.ok(userService.listBySelect(request));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 获取球队所有员信息
     *
     * @return ok
     */
    @RequestMapping(value = "/listByTeamId", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseModel listByTeamId(@RequestBody UserRequest request) {
        try {
            return ResponseModel.ok(userService.list(request));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }


    /**
     * 获取所有球员信息，
     *
     * @return ok
     */
    @RequestMapping(value = "/listPlayer", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseModel listPlayer(@RequestBody UserRequest request) {
        try {
            return ResponseModel.ok(userService.listPlayer(request));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 获取所有教练员
     *
     * @return ok
     */
    @RequestMapping(value = "/listTeacher", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseModel listTeacher(@RequestBody UserRequest request) {
        try {
            return ResponseModel.ok(userService.listTeacher(request));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }


    /**
     * 管理员添加添加用户（供管理员添加使用）
     *
     * @return ok
     */
    @RequestMapping(value = "/adminAddUser", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel adminAddUser(@RequestBody UserRequest model) {
        try {
            userService.add(BaseContextHandler.getCustomerId(), model);
            return ResponseModel.ok();
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 用户注册（自己注册）
     *
     * @return ok
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseModel register(@RequestBody UserRequest model) {
        try {
            userService.add(model);
            return ResponseModel.ok();
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
    public ResponseModel edit(@RequestBody UserRequest model) {
        try {
            userService.edit(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel delete(@RequestBody UserRequest model) {
        try {
            userService.delete(BaseContextHandler.getCustomerId(), model);
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
    @Authorization(onlyGetCustomer = true)
    public ResponseModel detailed(@RequestBody UserRequest model) {
        try {

            return ResponseModel.ok(userService.detailed(model));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 用户登录
     *
     * @return ok
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseModel login(@RequestBody UserRequest model) {
        try {
            return ResponseModel.ok(userService.login(model));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

    /**
     * 获取所有球员信息key
     *
     * @return ok
     */
    @RequestMapping(value = "/listKey", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel listKey() {
        try {
            return ResponseModel.ok(userService.listKey());
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
        /**
         * 获取用户登录后自己的信息
         *
         * @return ok
         */
        @RequestMapping(value = "/myInfo", method = RequestMethod.POST, consumes = "application/json")
        public ResponseModel myInfo() {
            try {

                return ResponseModel.ok(userService.myInfo(BaseContextHandler.getCustomerId()));
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
    @RequestMapping(value = "/signOut", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel signOut() {
        try {
            userService.signOut(BaseContextHandler.getCustomerId());
            return ResponseModel.ok();
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }

}
