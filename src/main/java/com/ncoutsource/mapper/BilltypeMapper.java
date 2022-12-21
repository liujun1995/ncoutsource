package com.ncoutsource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 单据类型（bd_billtype）
 */
@Mapper
public interface BilltypeMapper {

    @Select("select pk_billtypecode from bd_billtype where pk_billtypeid=#{ctrantypeid} and nvl(dr,0)=0")
    String selectCodeByPk(@Param("ctrantypeid") String ctrantypeid);

}
