package com.nc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcTemplateDataSourceConfig {

//	@Primary
//	@Bean(name = "ncJdbcTemplate")
//	public JdbcTemplate ncJdbcTemplate(@Qualifier("ncDataSource") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//
//	@Bean(name = "wxwbJdbcTemplate")
//	public JdbcTemplate wxwbJdbcTemplate(@Qualifier("wxwbDataSource") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}

}
