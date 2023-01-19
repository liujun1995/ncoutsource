package com.nc.mapper.wxwb;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WxwbMapper {

	List<Map<String, Object>> query(@Param("whereSql") String whereSql);

	List<Map<String, Object>> queryMatterAndContractInfoWithoutContractlogId(@Param("contractId") String contractIds,
																			 @Param("materialCode") String materialCode,
																			 @Param("buySource") Integer buySource);

	List<Map<String, Object>> queryMatterAndContractInfoWithContractlogId(@Param("contractId") String contractIds,
																		  @Param("materialCode") String materialCode,
																		  @Param("buySource") Integer buySource);

}
