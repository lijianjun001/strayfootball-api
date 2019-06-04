package com.strayfootball.api.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author Karl
 * @create 2019/6/2 11:29
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserView {
    /**
     * 球员id
     */
    private int   id;
    /**
     * 球员名称
     */
    private String   name;
}
