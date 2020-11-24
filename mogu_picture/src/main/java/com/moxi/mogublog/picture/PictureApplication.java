package com.moxi.mogublog.picture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.oas.annotations.EnableOpenApi;

//图片服务，用于图片上传和下载
@EnableTransactionManagement// 开启注解事务管理
@SpringBootApplication
@EnableOpenApi//swagger3
@EnableDiscoveryClient//实现服务注册与发现
@EnableFeignClients("com.moxi.mogublog.commons.feign")//启用feign客户端，远程调用
@ComponentScan(basePackages = {
        "com.moxi.mogublog.commons.config.feign",
        "com.moxi.mogublog.commons.handler",
        "com.moxi.mogublog.commons.config.redis",
        "com.moxi.mogublog.utils",
        "com.moxi.mogublog.picture"})
public class PictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureApplication.class, args);
    }

    //Spring内部的一种配置方式
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //配置允许跨域访问的路径
                registry.addMapping("/**/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .exposedHeaders("")
                        .maxAge(3600);
            }
        };
    }
}
