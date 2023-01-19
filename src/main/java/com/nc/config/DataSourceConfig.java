package com.nc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	@Primary
	@Bean(name = "ncDataSourceProperties")
	@ConfigurationProperties("spring.datasource.nc")
	public DataSourceProperties ncDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "ncDataSource")
	public DataSource ncDataSource(@Qualifier("ncDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean(name = "wxwbDataSourceProperties")
	@ConfigurationProperties("spring.datasource.wxwb")
	public DataSourceProperties wxwbDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "wxwbDataSource")
	public DataSource wxwbDataSource(@Qualifier("wxwbDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

}
