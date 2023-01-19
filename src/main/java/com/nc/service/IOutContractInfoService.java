package com.nc.service;

import java.util.List;
import java.util.Map;

import com.nc.entity.wxwb.OutContractInfo;

public interface IOutContractInfoService {


	
	OutContractInfo queryByContractId(String contractId);
	
}
