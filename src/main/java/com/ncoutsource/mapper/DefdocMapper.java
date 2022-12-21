package com.ncoutsource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * bd_defdoc
 */
@Mapper
public interface DefdocMapper {
    @Select("select code from bd_defdoc where pk_defdoc=#{vbdef17} and nvl(dr,0)=0")
    Map<String,Object> selectQICodeByPk(@Param("vbdef17") String vbdef17);


    @Select("select code from bd_defdoc where pk_defdoc=#{vdef5} and nvl(dr,0)=0")
    Map<String,Object> selectOutsourceCodeByPk(@Param("vdef5") String vdef5);
}
