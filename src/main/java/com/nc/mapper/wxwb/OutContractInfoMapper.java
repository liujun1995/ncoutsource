package com.nc.mapper.wxwb;


import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nc.entity.wxwb.OutContractInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OutContractInfoMapper extends BaseMapper<OutContractInfo> {

    @Select("select SUPPLIER_CODE from OUT_CONTRACT_INFO where CONTRACT_ID=#{contractId}")
    String querySupplierCodeWithContractId(@Param("contractId") String contractId);

	
}
