package com.example.demo.conf;



import com.example.demo.incerceptor.ResourceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  配置静态资源拦截器
 */

@Configuration
public class ResourceConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResourceInterceptor()).excludePathPatterns("/static/**");
        registry.addInterceptor(new ResourceInterceptor()).excludePathPatterns("/video/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 上传路径
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ TaleUtils.getUplodFilePath()+"upload/");
        // 第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/video/**").addResourceLocations("file:./video/");

    }

}

