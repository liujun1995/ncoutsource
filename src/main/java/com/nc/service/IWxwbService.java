package com.nc.service;

import com.nc.entity.CheckRules;
import com.nc.entity.wxwb.OutContractInfo;

import java.util.List;
import java.util.Map;

public interface IWxwbService {

	List<Map<String, Object>> query(String whereSql);

	int quote(String contractIds);

	void checkRules(CheckRules checkRules);

	List<Map<String, Object>> queryMatterContractInfo(String contractIds, String matterId,String materialCode, String fromM20);

	int unQuote(String contractIds);

	OutContractInfo queryContractInfoById(String contractId);

	List<Map<String, Object>> queryContractAndMatterInfo(String whereSql);

	//引用后返回contractId和matterInfoId各自影响的行数
	Map<String,Integer> quoteMattersAndContracts(String contractIds, String matterInfoIds);

	//取消引用后返回contractId和matterInfoId各自影响的行数
	Map<String,Integer> unQuoteMattersAndContracts(String contractIds, String matterInfoIds);

	//取消引用后返回matterInfoId影响的行数
	Map<String,Integer> unQuoteMatters(String matterInfoIds);

	//采购订单保存时,检查供应商是否一致
	List<Map<String, String>> CheckOutContractSupplierSame(String contractIds);
}
