package com.ssafy.ssafitlife.food.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("foodDBConfig")
@MapperScan(basePackages = "com.ssafy.ssafitlife.food.model.dao")
public class DBConfig {
}
