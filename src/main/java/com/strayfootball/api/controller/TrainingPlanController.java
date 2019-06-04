package com.strayfootball.api.controller;

import com.strayfootball.api.authorization.context.BaseContextHandler;
import com.strayfootball.api.dto.ResponseModel;
import com.strayfootball.api.dto.request.physical.TestPhysicalRequest;
import com.strayfootball.api.dto.request.plan.TrainingPlanRequest;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.strayfootball.api.dto.ResponseModel.error;

/**
 * description
 * 训练计划控制器
 *
 * @author Karl
 * @create 2019/6/1 23:20
 */
@RestController
@RequestMapping("/api/trainingPlan")
public class TrainingPlanController {
    private final TrainingPlanService trainingPlanService;

    @Autowired
    public TrainingPlanController(TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }


    /**
     * 获取所有信息
     *
     * @return ok
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel list(@RequestBody TrainingPlanRequest model) {
        try {
            return ResponseModel.ok(trainingPlanService.list(model));
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
    public ResponseModel add(@RequestBody TrainingPlanRequest model) {
        try {
            trainingPlanService.add(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel edit(@RequestBody TrainingPlanRequest model) {
        try {
            trainingPlanService.update(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel delete(@RequestBody TrainingPlanRequest model) {
        try {
            trainingPlanService.delete(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel detailed(@RequestBody TrainingPlanRequest model) {
        try {
            return ResponseModel.ok(trainingPlanService.detailed(model));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
    /**
     * 获取所有训练计划 key
     *
     * @return ok
     */
    @RequestMapping(value = "/listKey", method = RequestMethod.POST, consumes = "application/json")
    public ResponseModel listKey() {
        try {
            return ResponseModel.ok(trainingPlanService.listKey());
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
}
