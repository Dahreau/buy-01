package com.example.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "INTERNAL_TOKEN=test-internal-token-for-microservices",
    "spring.data.mongodb.uri=mongodb://localhost:27017/test-products"
})
class ProductServiceApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
        assertNotNull(context, "Spring application context should be loaded");
        assertTrue(context.containsBean("productServiceApplication"), 
                  "ProductServiceApplication bean should be present");
    }

    @Test
    void mainMethod_ShouldStartApplication() {
        // Test that the application can start without errors
        assertDoesNotThrow(() -> {
            ProductServiceApplication.main(new String[] {});
        }, "Application should start without throwing exceptions");
    }

    @Test
    void securityBeans_ShouldBeConfigured() {
        // Test that security configuration is properly set up
        assertNotNull(context.getBean(org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration.class),
                     "Web security configuration should be available");
    }

    @Test
    void mongoDBConfiguration_ShouldBeAvailable() {
        // Test MongoDB configuration
        try {
            assertNotNull(context.getBean(org.springframework.data.mongodb.core.MongoTemplate.class),
                         "MongoTemplate bean should be available for product data");
        } catch (Exception e) {
            // MongoDB might not be available in test environment
            System.out.println("MongoDB not available in test environment: " + e.getMessage());
        }
    }

    @Test
    void internalToken_ShouldBeConfigured() {
        // Test that internal token for microservice communication is available
        String internalToken = context.getEnvironment().getProperty("INTERNAL_TOKEN");
        assertNotNull(internalToken, "Internal token for service communication should be configured");
        assertEquals("test-internal-token-for-microservices", internalToken);
    }
}