package com.nc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * bd_measdoc
 */
@Mapper
public interface MeasdocMapper {

    @Select("select code,name from bd_measdoc where pk_measdoc=#{pk_measdoc} and nvl(dr,0)=0")
    Map<String,Object> selectMeasCodeAndNameByPk(@Param("pk_measdoc") String pk_measdoc);
}
