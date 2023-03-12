package com.nc.service;

import com.baomidou.mybatisplus.service.IService;
import com.nc.entity.PraybillDetail;

public interface IPraybillDetailService extends IService<PraybillDetail> {


    int selectPkPraybillB(String pk_praybill_b);

}
