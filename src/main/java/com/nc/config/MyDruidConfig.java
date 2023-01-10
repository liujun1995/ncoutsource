package com.nc.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class MyDruidConfig {

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druidDatasource() {
		return new DruidDataSource();
	}

	// 配置 Druid 监控管理后台的Servlet；
	// 内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 这些参数可以在 com.alibaba.druid.support.http.StatViewServlet 的父类
		// com.alibaba.druid.support.http.ResourceServlet 中找到
		Map<String, String> initParams = new HashMap<>();
		initParams.put("loginUsername", "root");
		initParams.put("loginPassword", "abc+123456");
		initParams.put("allow", ""); // 默认就是允许所有访问
		// initParams.put("deny", "192.168.10.132"); //deny：Druid 后台拒绝谁访问，表示禁止此ip访问
		initParams.put("resetEnable", "false"); // 是否可以重置数据
		bean.setInitParameters(initParams);
		return bean;
	}

	// 2、配置一个web监控的filter
	@Bean
	public FilterRegistrationBean webStatFilter() {
		// 创建过滤器
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<>();
		// 忽略过滤的形式
		initParams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		bean.setInitParameters(initParams);
		// 设置过滤路径
		bean.setUrlPatterns(Collections.singletonList("/*"));
		return bean;
	}

}
