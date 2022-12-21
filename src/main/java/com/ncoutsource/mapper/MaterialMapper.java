package com.ncoutsource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 物料bd_material
 */
@Mapper
public interface MaterialMapper {

    @Select("select code,name,version,materialspec from bd_material where pk_material=#{pk_material} and nvl(dr,0)=0")
    Map<String,Object> selectMaterialCodeAndNameAndVersionAndSpecByPk(@Param("pk_material") String pk_material);
}
