package com.example.springbootrestapi.config;
 
import org.mybatis.spring.annotation.MapperScan; 
import org.springframework.context.annotation.Configuration; 

import com.example.springbootrestapi.SpringbootRestapiApplication;
 
@Configuration 
@MapperScan(basePackageClasses = SpringbootRestapiApplication.class)
public class DataSourceConfig { 
 
} 
