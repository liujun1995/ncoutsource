package com.nc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 人员(bd_psndoc)
 */
@Mapper
public interface PsndocMapper {

    @Select("select code from bd_psndoc where pk_psndoc=#{pk_planpsn} and nvl(dr,0)=0")
    Map<String,Object> selectPsnCodeByPk(@Param("pk_planpsn") String pk_planpsn);

}
