package com.chinaasia.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@Configuration
public class MyFilterConfig {
    /**
     * 配置过滤器
     * @return
     */
    /*
    @WebFilter(filterName = "corsFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "allowOrigin", value = "*"),
                @WebInitParam(name = "allowMethods", value = "GET,POST,PUT,DELETE,OPTIONS"),
                @WebInitParam(name = "allowCredentials", value = "true"),
                @WebInitParam(name = "allowHeaders", value = "Content-Type,X-Token")})
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("allowOrigin", "*");
        registration.addInitParameter("allowMethods", "GET,POST,PUT,DELETE,OPTIONS");
        registration.addInitParameter("allowCredentials", "true");
        registration.addInitParameter("allowHeaders", "Content-Type,X-Token");
        registration.setName("corsFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "corsFilter")
    public Filter corsFilter() {
        return new CorsFilter();
    }
}