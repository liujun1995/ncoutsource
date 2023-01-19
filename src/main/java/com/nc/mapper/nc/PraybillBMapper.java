package com.nc.mapper.nc;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nc.entity.PraybillB;
import com.nc.entity.PraybillDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PraybillBMapper extends BaseMapper<PraybillB> {

   /**
    * 查询没有抓取到中间表的,以及表体行不是行关闭状态的
    * @param pk_praybill
    * @return
    */
   @Select("select * from po_praybill_b where pk_praybill=#{pk_praybill} and nvl(dr,0)=0 and vbdef15='N' and browclose='N'")
   List<PraybillB> selectPraybillBsByPraybillPk(@Param("pk_praybill") String pk_praybill);

   Integer updatePraybillBsVbdef(List<PraybillDetail> filtedPraybillDetails);

}
