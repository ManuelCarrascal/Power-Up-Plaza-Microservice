package com.pragma.powerup.infrastructure.feign;

import com.pragma.powerup.infrastructure.utils.constants.UserFeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = UserFeignConstants.FEIGN_CLIENT_NAME,
        url = UserFeignConstants.BASE_URL,
        configuration = FeignConfig.class
)
public interface IUserFeignClient {
    @GetMapping(
            value = UserFeignConstants.USER_IS_OWNER_PATH,
            produces = UserFeignConstants.CONTENT_TYPE_JSON
    )
    Boolean findOwnerById(@RequestParam(UserFeignConstants.OWNER_ID_PARAM) Long id);
}



