package com.example.mediaservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MediaServiceApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
        assertNotNull(context, "Spring application context should be loaded");
        assertTrue(context.containsBean("mediaServiceApplication"), 
                  "MediaServiceApplication bean should be present");
    }

    @Test
    void mainMethod_ShouldStartApplication() {
        // Test that the application can start without errors
        assertDoesNotThrow(() -> {
            MediaServiceApplication.main(new String[] {});
        }, "Application should start without throwing exceptions");
    }
}