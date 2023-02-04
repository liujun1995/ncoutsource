package com.nc.controller;

import java.util.List;
import java.util.Map;

import com.nc.entity.CheckRules;
import org.springframework.web.bind.annotation.*;
import com.nc.aspect.CheckRule;
import com.nc.common.CommonResult;
import com.nc.service.IWxwbService;
import com.nc.utils.JsonUtils;
import javax.annotation.Resource;


@RestController
@RequestMapping("/wxwb")
public class WxwbController {


	@Resource
	private IWxwbService wxwbService;


	@GetMapping("/query")
	public CommonResult query(@RequestParam("whereSql") String whereSql) {
		List<Map<String, Object>> items = wxwbService.query(whereSql);
		return CommonResult.success(JsonUtils.listToJSONArray(items));
	}

	/**
	 * 引用合同
	 * @param params 引用规则
	 * @return
	 */
	@CheckRule
	@PostMapping("/quote")
	public CommonResult quote(@RequestBody Map<String, Object> params) {
		return CommonResult.success(wxwbService.quote(JsonUtils.convertMapToBean(CheckRules.class, params).getContractIds()));
	}

	@CheckRule
	@PostMapping("/unQuote")
	public CommonResult unQuote(@RequestParam("rules") String rules, @RequestParam("contractIds") String contractIds) {
		return CommonResult.success(wxwbService.unQuote(contractIds));
	}

	@CheckRule
	@GetMapping("/queryContractInfoById")
	public CommonResult queryContractInfoById(@RequestParam("rules") String rules,
										  @RequestParam("contractId") String contractId){
		return CommonResult.success(wxwbService.queryContractInfoById(contractId));

	}


	@CheckRule
	@GetMapping("/queryMatterContractInfo")
	public CommonResult queryMatterContractInfo(@RequestParam("rules") String rules,
												 @RequestParam("contractIds") String contractIds,
												 @RequestParam("materialCode") String materialCode,
												 @RequestParam("fromM20") String fromM20){
		List<Map<String, Object>> items = wxwbService.queryMatterContractInfo(contractIds, materialCode, fromM20);
		return CommonResult.success(JsonUtils.listToJSONArray(items));

	}
}
