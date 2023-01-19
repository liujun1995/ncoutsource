package com.nc.mapper.nc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 根据请购单表头的库存组织code查询出pk
 */
@Mapper
public interface StockorgVMapper {

    @Select("select pk_vid from org_stockorg_v where code=#{StockorgCode} and nvl(dr,0)=0")
    String selectStockorgPkByCode(@Param(value = "StockorgCode") String StockorgCode);

}
