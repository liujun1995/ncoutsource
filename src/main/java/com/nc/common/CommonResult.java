package com.nc.common;

import lombok.Data;

@Data
public class CommonResult {

	private int code;
	private Object data;
	private String message;

	private CommonResult(int code, Object data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public static CommonResult success(Object data) {
		return new CommonResult(200, data, "中间件：" + "操作成功");
	}

	public static CommonResult failed(String message) {
		return new CommonResult(500, null, "中间件：" + message);
	}

}
