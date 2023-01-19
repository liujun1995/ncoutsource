package com.nc.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;

@Configuration
public class MyBatisPlusConfig {

	@MapperScan(basePackages = "com.nc.mapper.nc", sqlSessionTemplateRef = "ncSqlSessionTemplate")
	class NC {
		@Primary
		@Bean(name = "ncSqlSessionFactory")
		public SqlSessionFactory ncSqlSessionFactory(@Qualifier("ncDataSource") DataSource dataSource)
				throws Exception {
			MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setMapperLocations(
					new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/nc/*/*Mapper.xml"));

			MybatisConfiguration configuration = new MybatisConfiguration();
			configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
			configuration.setJdbcTypeForNull(JdbcType.NULL);
			sqlSessionFactoryBean.setConfiguration(configuration);

			sqlSessionFactoryBean
					.setPlugins(new Interceptor[] { new PaginationInterceptor(), new PerformanceInterceptor() });

			return sqlSessionFactoryBean.getObject();
		}

		@Primary
		@Bean(name = "ncTransactionManager")
		public DataSourceTransactionManager ncTransactionManager(@Qualifier("ncDataSource") DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}

		@Primary
		@Bean(name = "ncSqlSessionTemplate")
		public SqlSessionTemplate ncSqlSessionTemplate(
				@Qualifier("ncSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}

	@MapperScan(basePackages = "com.nc.mapper.wxwb", sqlSessionTemplateRef = "wxwbSqlSessionTemplate")
	class WXWB {
		@Bean(name = "wxwbSqlSessionFactory")
		public SqlSessionFactory wxwbSqlSessionFactory(@Qualifier("wxwbDataSource") DataSource dataSource)
				throws Exception {
			MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setMapperLocations(
					new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/wxwb/*Mapper.xml"));

			MybatisConfiguration configuration = new MybatisConfiguration();
			configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
			configuration.setJdbcTypeForNull(JdbcType.NULL);
			sqlSessionFactoryBean.setConfiguration(configuration);

			sqlSessionFactoryBean
					.setPlugins(new Interceptor[] { new PaginationInterceptor(), new PerformanceInterceptor() });

			return sqlSessionFactoryBean.getObject();
		}

		@Bean(name = "wxwbTransactionManager")
		public DataSourceTransactionManager wxwbTransactionManager(@Qualifier("wxwbDataSource") DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}

		@Bean(name = "wxwbSqlSessionTemplate")
		public SqlSessionTemplate wxwbSqlSessionTemplate(
				@Qualifier("wxwbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}

}
