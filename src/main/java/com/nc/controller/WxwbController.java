package com.nc.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

	@CheckRule
	@PostMapping("/quote")
	public CommonResult quote(@RequestParam("rules") String rules, @RequestParam("contractIds") String contractIds) {
		return CommonResult.success(wxwbService.quote(contractIds));
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
