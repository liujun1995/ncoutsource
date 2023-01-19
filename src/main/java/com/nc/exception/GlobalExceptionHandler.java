package com.nc.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nc.common.CommonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public CommonResult exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
		String message = exception.getMessage() + request.getRequestURL().toString();
		log.error(message);
		return CommonResult.failed(message);
	}

}