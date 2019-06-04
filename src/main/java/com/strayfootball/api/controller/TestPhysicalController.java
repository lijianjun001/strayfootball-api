package com.strayfootball.api.controller;

import com.strayfootball.api.authorization.context.BaseContextHandler;
import com.strayfootball.api.dto.ResponseModel;
import com.strayfootball.api.dto.request.physical.TestPhysicalRequest;
import com.strayfootball.api.dto.request.train.UserTrainRequest;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.TestPhysicalService;
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
@RequestMapping("/api/testPhysical")
public class TestPhysicalController {
    private final TestPhysicalService testPhysicalService;
    @Autowired
    public TestPhysicalController(TestPhysicalService testPhysicalService) {
        this.testPhysicalService = testPhysicalService;
    }

    /**
     * 获取所有信息
     *
     * @return ok
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel list() {
        try {
            return ResponseModel.ok(testPhysicalService.list());
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
    public ResponseModel add(@RequestBody TestPhysicalRequest model) {
        try {
            testPhysicalService.add(BaseContextHandler.getCustomerId(),model);
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
    public ResponseModel edit(@RequestBody TestPhysicalRequest model) {
        try {
            testPhysicalService.update(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel delete(@RequestBody TestPhysicalRequest model) {
        try {
            testPhysicalService.delete(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel detailed(@RequestBody TestPhysicalRequest model) {
        try {
            return ResponseModel.ok( testPhysicalService.detailed(model));
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
    @RequestMapping(value = "/myPhysicalInfo", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel myPhysicalInfo() {
        try {
            return ResponseModel.ok( testPhysicalService.myPhysicalInfo(BaseContextHandler.getCustomerId()));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
}
