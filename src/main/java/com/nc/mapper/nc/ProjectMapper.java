package com.nc.mapper.nc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface ProjectMapper {

    @Select("select project_code,project_name,def1,def16 from bd_project where pk_project=#{cprojectid} and nvl(dr,0)=0")
    Map<String,Object> selectCodeAndNameAndSymbolAndNumberByPk(@Param("cprojectid") String cprojectid);

}
