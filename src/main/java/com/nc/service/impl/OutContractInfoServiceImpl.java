package com.nc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nc.entity.wxwb.OutContractInfo;
import com.nc.mapper.wxwb.OutContractInfoMapper;
import com.nc.service.IOutContractInfoService;

import javax.annotation.Resource;

@Service
public class OutContractInfoServiceImpl extends ServiceImpl<OutContractInfoMapper, OutContractInfo> implements IOutContractInfoService {

	@Resource
	private OutContractInfoMapper outContractInfoMapper;
	


	@Override
	public OutContractInfo queryByContractId(String contractId) {
		return super.baseMapper.selectById(contractId);
	}
	
}
