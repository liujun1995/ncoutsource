package com.nc.mapper.nc;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nc.entity.Praybill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
public interface PraybillMapper extends BaseMapper<Praybill> {

    List<Praybill> selectPraybillsByCondition(@Param("fbillstatus") String fbillstatus,
                                              @Param("bislatest") String bislatest,
                                              @Param("dr") String dr,
                                              @NotNull @Param("pk_org_v") String pk_org_v,
                                              @Param("vdef1") String vdef1);

    Integer updatePraybillVbdef(@NotNull @Param("pk_praybill") String pk_praybill);

}
