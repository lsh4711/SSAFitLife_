package com.ssafy.ssafitlife.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("userDBConfig")
@MapperScan(basePackages = "com.ssafy.ssafitlife.user.model.dao")
public class DBConfig {
}
