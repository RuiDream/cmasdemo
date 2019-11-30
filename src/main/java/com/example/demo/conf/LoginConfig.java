package com.example.demo.conf;

import com.example.demo.incerceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    /**
     * 不需要登录拦截的url
     */
    final String[] notLoginInterceptPaths = {"/static/mas/html/login-zmm/login.html","/static/mas/css/**", "/static/mas/js.video/**","/static/layui/**","/error/**", "/login/**", "/register/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**.html").excludePathPatterns(notLoginInterceptPaths);
    }

}
