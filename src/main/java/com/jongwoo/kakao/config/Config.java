package com.jongwoo.kakao.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan(basePackages = {"com.jongwoo.kakao.service"})
@ComponentScan(basePackages = {"com.jongwoo.kakao.repository"})
@ComponentScan(basePackages = {"com.jongwoo.kakao.vo"})
@ComponentScan(basePackages = {"com.jongwoo.kakao.controller"})
@EntityScan("com.jongwoo.kakao.vo")
public class Config {

}
