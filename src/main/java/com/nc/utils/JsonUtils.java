package com.nc.utils;

import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class JsonUtils {

	public static JSONArray listToJSONArray(Object list) {
		if(list instanceof List)
			return JSONArray.parseArray(JSONObject.toJSONString(list));
		return new JSONArray();
	}
	
}
