package com.nc.mapper.wxwb;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WxwbMapper {

	List<Map<String, Object>> query(@Param("whereSql") String whereSql);

	//合同变更id为空
	List<Map<String, Object>> queryMatterAndContractInfoWithoutContractlogId(@Param("contractId") String contractIds,
																			 @Param("matterId") String matterId,
																			 @Param("materialCode") String materialCode,
																			 @Param("buySource") Integer buySource);
	//合同变更id不为空
	List<Map<String, Object>> queryMatterAndContractInfoWithContractlogId(@Param("contractId") String contractIds,
																		  @Param("matterId") String matterId,
																		  @Param("materialCode") String materialCode,
																		  @Param("buySource") Integer buySource);

	List<Map<String, Object>> queryContractAndMatterInfo(@Param("whereSql") String whereSql);
}
