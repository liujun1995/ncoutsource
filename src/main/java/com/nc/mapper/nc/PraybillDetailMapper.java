package com.nc.mapper.nc;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nc.entity.PraybillDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PraybillDetailMapper extends BaseMapper<PraybillDetail> {

    @Select("select count(1) from praybill_detail where pk_praybill_b=#{pk_praybill_b}")
    int selectPkPraybillB(@Param("pk_praybill_b") String pk_praybill_b);

}