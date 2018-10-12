package com.shoes101.config.WebConfigurer;

/**
 * 拦截器
 */

import com.shoes101.access.AccessInterceptor;
import com.shoes101.access.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.List;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    AccessInterceptor accessInterceptor;

    @Autowired
    UserArgumentResolver userArgumentResolver;
    @Autowired
    AdminArgumentResolver adminArgumentResolver;

    @Autowired
    AdminInterceptor adminInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //argumentResolvers.add(userArgumentResolver);
        //argumentResolvers.add(adminArgumentResolver);
    }

    public void addInterceptors(InterceptorRegistry registry)
    {
        /**
         * 新增拦截器
         */
        //registry.addInterceptor(/*new一个拦截器*/).addPathPatterns(/*控制器*/);
        //super.addInterceptors(registry);
        registry.addInterceptor(accessInterceptor);
        registry.addInterceptor(adminInterceptor);

    }
}
