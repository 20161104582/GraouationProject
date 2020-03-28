package com.web.sys.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(new CheckLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/favicon**") //
                .excludePathPatterns("/front/*") //front/detail
                .excludePathPatterns("/front/notice") //front/detail
                .excludePathPatterns("/register") //
                .excludePathPatterns("/login/**") //
                .excludePathPatterns("/reg/**") //
                .excludePathPatterns("/dataInfo/**") //
                .excludePathPatterns("/static/**") //
                .excludePathPatterns("/api/log/**")
                .excludePathPatterns("/front/**")
                .excludePathPatterns("/api/front/**")
                .excludePathPatterns("/api/odr/**")
        ;
        super.addInterceptors(registry);
    }
}