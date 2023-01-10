package com.nc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * bd_supplier
 */
@Mapper
public interface SupplierMapper {

    @Select("select code,name from bd_supplier where pk_supplier=#{pk_suggestsupplier} and nvl(dr,0)=0")
    Map<String,Object> selectSupplierCodeAndName(@Param("pk_suggestsupplier")
                                                         String pk_suggestsupplier);

}
