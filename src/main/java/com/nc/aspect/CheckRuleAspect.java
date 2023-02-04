package com.nc.aspect;

import java.util.HashMap;
import java.util.Map;

import com.nc.entity.CheckRules;
import com.nc.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.nc.service.IWxwbService;
import javax.annotation.Resource;


@Aspect
@Component
@Order(1)
public class CheckRuleAspect {

	@Resource
	private IWxwbService wxwbService;

	@Pointcut("@annotation(com.nc.aspect.CheckRule)")
	public void checkRulePointcut() {
	}

	@Before(value = "checkRulePointcut()")
	public void doBefore(JoinPoint joinPoint) {
		Map<String, Object> params = getRequestParams(joinPoint);
		CheckRules checkRules = JsonUtils.convertMapToBean(CheckRules.class, params);
		wxwbService.checkRules(checkRules);
	}

	private Map<String, Object> getRequestParams(JoinPoint joinPoint) {
		if (1 == joinPoint.getArgs().length && joinPoint.getArgs()[0] instanceof Map) {
			return (Map<String, Object>) joinPoint.getArgs()[0];
		}
		Map<String, Object> params = new HashMap<>();
		String[] keyArr = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		Object[] valArr = joinPoint.getArgs();
		for (int i = 0; i < keyArr.length; i++) {
			params.put(keyArr[i], valArr[i]);
		}
		return params;
	}

}
