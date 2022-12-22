package com.ncoutsource.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ncoutsource.entity.PraybillB;
import com.ncoutsource.entity.PraybillDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface PraybillBMapper extends BaseMapper<PraybillB> {

   @Select("select * from po_praybill_b where pk_praybill=#{pk_praybill} and nvl(dr,0)=0 and vbdef14!='Y'")
   List<PraybillB> selectPraybillBsByPraybillPk(@Param("pk_praybill") String pk_praybill);

   Integer updatePraybillBsVbdef(List<PraybillDetail> filtedPraybillDetails);
}
