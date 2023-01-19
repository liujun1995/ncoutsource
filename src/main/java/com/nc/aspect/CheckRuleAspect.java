package com.nc.aspect;

import java.util.HashMap;
import java.util.Map;
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
		Map<String, Object> params = this.getRequestParams(joinPoint);
		String rules = String.valueOf(params.get("rules"));
		String contractIds = String.valueOf(params.get("contractIds"));
		wxwbService.checkRules(contractIds, rules);
	}

	private Map<String, Object> getRequestParams(JoinPoint joinPoint) {
		Map<String, Object> params = new HashMap<>();
		if (null == joinPoint || null == joinPoint.getArgs() || 1 > joinPoint.getArgs().length)
			return new HashMap<String, Object>();

		String[] keyArr = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		Object[] valArr = joinPoint.getArgs();
		for (int i = 0; i < keyArr.length; i++) {
			params.put(keyArr[i], valArr[i]);
		}
		return params;
	}

}
