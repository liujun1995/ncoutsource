package com.ncoutsource.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * org_dept_v
 */
@Mapper
public interface DeptVMapper {

    @Select("select code from org_dept_v where pk_vid =#{pk_reqdept_v} and nvl(dr,0)=0")
    String selectDeptVCodeByPk(@Param("pk_reqdept_v") String pk_reqdept_v);


}
