package com.nc.service;

import com.nc.entity.CheckRules;
import com.nc.entity.wxwb.OutContractInfo;

import java.util.List;
import java.util.Map;

public interface IWxwbService {

	List<Map<String, Object>> query(String whereSql);

	int quote(String contractIds);

	void checkRules(CheckRules checkRules);

	List<Map<String, Object>> queryMatterContractInfo(String contractIds, String materialCode, String fromM20);

	int unQuote(String contractIds);

	OutContractInfo queryContractInfoById(String contractId);
}
