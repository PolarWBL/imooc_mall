package com.imooc.mall.config;

import com.imooc.mall.filter.AdminFilter;
import com.imooc.mall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User过滤器 的配置
 * @author Boliang Weng
 */
@Configuration
public class UserFilterConfig {

    @Bean
    public UserFilter userFilter(){
        return new UserFilter();
    }

    @Bean(name = "userFilterConf")
    public FilterRegistrationBean<UserFilter> userFilterConfig() {
        FilterRegistrationBean<UserFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(userFilter());
        filterRegistrationBean.addUrlPatterns("/cart/*");
        filterRegistrationBean.addUrlPatterns("/order/*");
        filterRegistrationBean.setName("userFilterConf");
        return filterRegistrationBean;
    }

}
