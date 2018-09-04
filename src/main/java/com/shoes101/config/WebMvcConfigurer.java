package com.shoes101.config;

/**
 * 拦截器
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry)
    {
        /**
         * 新增拦截器
         */
        //registry.addInterceptor(/*new一个拦截器*/).addPathPatterns(/*控制器*/);
        //super.addInterceptors(registry);
    }
}
