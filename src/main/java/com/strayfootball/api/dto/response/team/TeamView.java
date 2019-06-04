package com.strayfootball.api.dto.response.team;

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
public class TeamView {
    /**
     * 球队id
     */
    private int   id;
    /**
     * 球队名称
     */
    private String   name;
}
