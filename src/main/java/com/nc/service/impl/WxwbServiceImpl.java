package com.nc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.nc.entity.CheckRules;
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

	@Transactional(value = "wxwbTransactionManager")
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

	@Transactional(value = "wxwbTransactionManager")
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
	public List<Map<String, Object>> queryContractAndMatterInfo(String whereSql) {
		return wxwbMapper.queryContractAndMatterInfo(whereSql);
	}


	@Override
	public void checkRules(CheckRules checkRules) {
		// 通用规则 校验合同是否可以正常使用
		if (null != checkRules.getContractIds()) {
			Wrapper<OutContractInfo> queryWhere = new EntityWrapper<OutContractInfo>();
			queryWhere.in("CONTRACT_ID", Arrays.asList(checkRules.getContractIds().split(",")));
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

		// 自定义规则
		if (null != checkRules.getRules()) {
			String[] ruleArr = checkRules.getRules().split(",");
			for (String rule : ruleArr) {
				if (!VerificationRulesEnum.vals().containsKey(rule))
					throw new RuntimeException("不存在验证规则，操作失败");

				// 工序委外合同传输接口规则：校验工序所剩金额是否足够
				if (VerificationRulesEnum.val2.getVal().equals(rule)) {
					String[] approvalIdArr = checkRules.getApprovalIds().split(",");
					String[] norigtaxmnyArr = checkRules.getNorigtaxmny().split(",");
					List<String> message = new ArrayList<String>();
					for (int i = 0; i < approvalIdArr.length; i++) {
						OutContractInfo outContractInfo = new OutContractInfo();
						outContractInfo.setApprovalId(approvalIdArr[i]);
						OutContractInfo oci = outContractInfoMapper.selectOne(outContractInfo);
						if (Double.parseDouble(oci.getContractAmount()) - Double.parseDouble(oci.getPaidAmount()) < Double.parseDouble(norigtaxmnyArr[i]))
							message.add(oci.getApprovalBillnumber());
					}
					if (0 < message.size())
						throw new RuntimeException(String.format("%s所剩金额不足，请检查", message.toString()));
				}
			}
		}
	}

	/**/
	@Override
	public List<Map<String, Object>> queryMatterContractInfo(String contractIds,String matterId, String materialCode, String fromM20) {

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
			return wxwbMapper.queryMatterAndContractInfoWithoutContractlogId(contractIds,matterId,materialCode,buySource);
		}
		return wxwbMapper.queryMatterAndContractInfoWithContractlogId(contractIds,matterId,materialCode,buySource);
	}




}
