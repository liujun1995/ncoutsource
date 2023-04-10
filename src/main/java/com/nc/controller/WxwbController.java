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

	/**
	 * 采购订单保存时,检查供应商是否一致
	 * @param rules
	 * @param contractIds
	 * @return
	 */
	@CheckRule
	@GetMapping("/CheckOutContractSupplierSame")
	public CommonResult CheckOutContractSupplierSame(@RequestParam("rules") String rules,
													 @RequestParam("contractIds") String contractIds){
		List<Map<String,String>> result =  wxwbService.CheckOutContractSupplierSame(contractIds);
		return CommonResult.success(JsonUtils.listToJSONArray(result));

	}


	@GetMapping("/query")
	public CommonResult query(@RequestParam("whereSql") String whereSql) {
		List<Map<String, Object>> items = wxwbService.query(whereSql);
		return CommonResult.success(JsonUtils.listToJSONArray(items));
	}

	@GetMapping("/queryContractAndMatterInfo")
	public CommonResult queryContractAndMatterInfo(@RequestParam("whereSql") String whereSql) {
		List<Map<String, Object>> items = wxwbService.queryContractAndMatterInfo(whereSql);
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

	/**
	 * 引用合同和物料
	 * @param params 引用规则
	 * @return
	 */
	@CheckRule
	@PostMapping("/quoteMattersAndContracts")
	public CommonResult quoteMattersAndContracts(@RequestBody Map<String, Object> params) {
		final CheckRules checkRules = JsonUtils.convertMapToBean(CheckRules.class, params);
		return CommonResult.success(wxwbService.quoteMattersAndContracts(checkRules.getContractIds(),checkRules.getMatterInfoIds()));
	}


	@CheckRule
	@PostMapping("/unQuote")
	public CommonResult unQuote(@RequestParam("rules") String rules, @RequestParam("contractIds") String contractIds) {
		return CommonResult.success(wxwbService.unQuote(contractIds));
	}

	/**
	 * 取消合同 和 物料的引用
	 * @param rules
	 * @param contractIds
	 * @param matterInfoIds
	 * @return
	 */
	@CheckRule
	@PostMapping("/unQuoteMattersAndContracts")
	public CommonResult unQuoteMattersAndContracts(@RequestParam("rules") String rules,
												   @RequestParam("contractIds") String contractIds,
												   @RequestParam("matterInfoIds") String matterInfoIds) {
		return CommonResult.success(wxwbService.unQuoteMattersAndContracts(contractIds,matterInfoIds));
	}


	@PostMapping("/unQuoteMatters")
	public CommonResult unQuoteMatters(@RequestParam("matterInfoIds") String matterInfoIds) {
		return CommonResult.success(wxwbService.unQuoteMatters(matterInfoIds));
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
												 @RequestParam("matterId") String matterId,
												 @RequestParam("materialCode") String materialCode,
												 @RequestParam("fromM20") String fromM20){
		List<Map<String, Object>> items = wxwbService.queryMatterContractInfo(contractIds,matterId,materialCode, fromM20);
		return CommonResult.success(JsonUtils.listToJSONArray(items));

	}
}
