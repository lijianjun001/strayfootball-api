package com.strayfootball.api.controller;

import com.strayfootball.api.authorization.annotation.Authorization;
import com.strayfootball.api.authorization.context.BaseContextHandler;
import com.strayfootball.api.dto.ResponseModel;
import com.strayfootball.api.dto.request.games.GamesRequest;
import com.strayfootball.api.dto.request.train.UserTrainRequest;
import com.strayfootball.api.exception.ApiException;
import com.strayfootball.api.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.strayfootball.api.dto.ResponseModel.error;

/**
 * description
 * 比赛赛事控制器
 *
 * @author Karl
 * @create 2019/6/1 23:20
 */
@RestController
@RequestMapping("/api/games")
public class GamesController {
    private final GamesService gamesService;

    @Autowired
    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    /**
     * 获取所有信息
     *
     * @return ok
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseModel list(@RequestBody GamesRequest model) {
        try {
            return ResponseModel.ok(gamesService.list(model));
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
    public ResponseModel add(@RequestBody GamesRequest model) {
        try {
            gamesService.add(BaseContextHandler.getCustomerId(),model);
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
    public ResponseModel edit(@RequestBody GamesRequest model) {
        try {
            gamesService.update(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel delete(@RequestBody GamesRequest model) {
        try {
            gamesService.delete(BaseContextHandler.getCustomerId(), model);
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
    public ResponseModel detailed(@RequestBody GamesRequest model) {
        try {
            return ResponseModel.ok(gamesService.detailed(model));
        } catch (ApiException ex) {
            return error(ex);
        } catch (Exception ex) {
            return error(ex, this.getClass());
        }
    }
}
