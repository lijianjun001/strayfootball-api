package com.strayfootball.api.dto.response.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description
 *计划  key
 * @author Karl
 * @create 2019/6/2 12:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanViewModel {
    private List<PlanView> list;
}
