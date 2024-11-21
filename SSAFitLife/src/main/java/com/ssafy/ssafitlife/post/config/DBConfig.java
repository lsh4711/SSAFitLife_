package com.ssafy.ssafitlife.post.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("postConfig")
@MapperScan(basePackages = "com.ssafy.ssafitlife.post.model.dao")
public class DBConfig {
}
