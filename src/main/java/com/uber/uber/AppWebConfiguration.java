package com.uber.uber;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Enable http request (configuration)
@Configuration
@EnableWebMvc
public class AppWebConfiguration implements WebMvcConfigurer {
}
