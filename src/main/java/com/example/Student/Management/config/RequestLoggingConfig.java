package com.example.Student.Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);  // Logs the client's IP address
        filter.setIncludeHeaders(true);    // Logs HTTP headers
        filter.setIncludePayload(true);    // Logs the body of the request
        filter.setMaxPayloadLength(10000); // Set the max length of the request payload
        filter.setAfterMessagePrefix("REQUEST DATA: ");  // Prefix for logged message
        return filter;
    }
}
