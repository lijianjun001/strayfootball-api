package com.strayfootball.api.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class UserViewModel {
    private List<UserView> list;
}
