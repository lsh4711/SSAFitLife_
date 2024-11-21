package com.ssafy.ssafitlife.activity.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("activityConfig")
@MapperScan(basePackages = "com.ssafy.ssafitlife.activity.model.dao")
public class DBConfig {
}
