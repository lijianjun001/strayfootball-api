package com.strayfootball.api.dto.response.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description
 *球队视图
 * @author Karl
 * @create 2019/6/2 11:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamViewModel {
    private List<TeamView> list;
}
