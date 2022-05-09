package com.imooc.mall.config;

import com.imooc.mall.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jws.Oneway;

/**
 * AdminFilter 的配置
 * @author Boliang Weng
 */
@Configuration
public class AdminFilterConfig {

    @Bean
    public AdminFilter adminFilter(){
        return new AdminFilter();
    }

    @Bean(name = "adminFilterConf")
    public FilterRegistrationBean<AdminFilter> adminFilterConfig() {
        FilterRegistrationBean<AdminFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(adminFilter());
        filterRegistrationBean.addUrlPatterns("/admin/category/*");
        filterRegistrationBean.addUrlPatterns("/admin/product/*");
        filterRegistrationBean.addUrlPatterns("/admin/order/*");
        filterRegistrationBean.setName("adminFilterConf");
        return filterRegistrationBean;
    }

}
