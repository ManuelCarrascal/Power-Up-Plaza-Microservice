package com.pragma.powerup.infrastructure.utils.constants;

public class UserFeignConstants {

    private UserFeignConstants() {
    }

    public static final String FEIGN_CLIENT_NAME = "power-up-user-microservice";
    public static final String BASE_URL = "http://localhost:8081/api/v1";
    public static final String USER_IS_OWNER_PATH = "/user/isOwner";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String OWNER_ID_PARAM = "ownerId";

}
