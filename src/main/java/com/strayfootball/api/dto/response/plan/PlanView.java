package com.strayfootball.api.dto.response.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author Karl
 * @create 2019/6/2 11:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanView {
    /**
     * 计划id
     */
    private int   id;
    /**
     * 训练计划标题
     */
    private String   title;
}
