package com.hypad.buysell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
