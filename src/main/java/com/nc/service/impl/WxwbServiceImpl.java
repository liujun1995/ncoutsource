package com.nc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nc.entity.wxwb.OutContractInfo;
import com.nc.enums.VerificationRulesEnum;
import com.nc.mapper.wxwb.OutContractInfoMapper;
import com.nc.mapper.wxwb.WxwbMapper;
import com.nc.service.IWxwbService;

import javax.annotation.Resource;

@Service
public class WxwbServiceImpl implements IWxwbService {

	@Resource
	private WxwbMapper wxwbMapper;

	@Resource
	private OutContractInfoMapper outContractInfoMapper;

	@Override
	public List<Map<String, Object>> query(String whereSql) {
		return wxwbMapper.query(whereSql);
	}

	@Transactional(value = "wxwbTransactionManager", timeout = 5)
	@Override
	public int quote(String contractIds) {

		if (null == contractIds || contractIds.replaceAll(" ", "").length() < 1)
			return 0;
		Wrapper<OutContractInfo> updateWhere = new EntityWrapper<OutContractInfo>();
		updateWhere.in("CONTRACT_ID", Arrays.asList(contractIds.split(",")));
		OutContractInfo outContractInfo = new OutContractInfo();
		outContractInfo.setIsUse(1);

		return this.outContractInfoMapper.update(outContractInfo, updateWhere);

	}

	@Transactional(value = "wxwbTransactionManager", timeout = 5)
	@Override
	public int unQuote(String contractIds) {

		if (null == contractIds || contractIds.replaceAll(" ", "").length() < 1)
			return 0;
		Wrapper<OutContractInfo> updateWhere = new EntityWrapper<OutContractInfo>();
		updateWhere.in("CONTRACT_ID", Arrays.asList(contractIds.split(",")));

		OutContractInfo outContractInfo = new OutContractInfo();
		outContractInfo.setIsUse(0);
		return this.outContractInfoMapper.update(outContractInfo, updateWhere);

	}

	@Override
	public OutContractInfo queryContractInfoById(String contractId) {
		return this.outContractInfoMapper.selectById(contractId);
	}


	@Override
	public void checkRules(String contractIds, String rules) {
		String[] ruleArr = rules.split(",");
		for (String rule : ruleArr) {
			if (!VerificationRulesEnum.vals().containsKey(rule))
				throw new RuntimeException("不存在为"+rule+"验证规则，操作失败");

			if (VerificationRulesEnum.val1.getVal().equals(rule) || VerificationRulesEnum.val2.getVal().equals(rule)) {
				Wrapper<OutContractInfo> queryWhere = new EntityWrapper<OutContractInfo>();
				queryWhere.in("CONTRACT_ID", Arrays.asList(contractIds.split(",")));
				queryWhere.notIn("STATE", Arrays.asList("0,1".split(",")));
				List<OutContractInfo> outContractInfos = outContractInfoMapper.selectList(queryWhere);
				if (null != outContractInfos && 0 < outContractInfos.size()) {
					List<String> projectNames = new ArrayList<String>();
					for (OutContractInfo outContractInfo : outContractInfos) {
						projectNames.add(outContractInfo.getProjectName());
					}
					throw new RuntimeException(String.format("%s合同未生效，请检查", projectNames.toString()));
				}
			}
		}
	}


	@Override
	public List<Map<String, Object>> queryMatterContractInfo(String contractIds, String materialCode, String fromM20) {
		OutContractInfo outContractInfo = outContractInfoMapper.selectById(contractIds);
		String contractlogId = outContractInfo.getContractlogId();
		int buySource;
		if("true".equalsIgnoreCase(fromM20)){
			//来源于请购单
			buySource = 0;
		}else{
			//不来源于请购单
			buySource = 1;
		}
		//contractlogId是null,合同有无contractlog_id
		if (null==contractlogId){
			return wxwbMapper.queryMatterAndContractInfoWithoutContractlogId(contractIds,materialCode,buySource);
		}
		return wxwbMapper.queryMatterAndContractInfoWithContractlogId(contractIds,materialCode,buySource);
	}




}
