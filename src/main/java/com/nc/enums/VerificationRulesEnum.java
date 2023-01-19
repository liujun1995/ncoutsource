package com.nc.enums;

import java.util.HashMap;
import java.util.Map;

public enum VerificationRulesEnum {

	val1("1", "立项信息、生产加工合格供方价格返回信息接口"), val2("2", "工序委外合同传输接口");

	private String val;
	private String desc;

	private VerificationRulesEnum(String val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private static Map<String, String> map = new HashMap<>();

	static {
		map.put(val1.val, val1.desc);
		map.put(val2.val, val2.desc);
	}

	public static Map<String, String> vals() {
		return map;
	}

}
