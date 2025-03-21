package com.pragma.powerup;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.main.lazy-initialization=true",
        "logging.level.root=OFF"
})
class PlazaMicroserviceAppTests {

    @Test
    void contextLoads() {
        // The method is intentionally left empty
    }
}