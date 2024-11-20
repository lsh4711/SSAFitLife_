package com.ssafy.ssafitlife.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("securityDBConfig")
@MapperScan(basePackages = "com.ssafy.ssafitlife.security.model.dao")
public class DBConfig {
}
