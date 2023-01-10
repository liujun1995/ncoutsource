package com.nc.service;

import com.baomidou.mybatisplus.service.IService;
import com.nc.entity.PraybillB;
import com.nc.entity.PraybillDetail;

import java.util.List;


public interface IPraybillBService extends IService<PraybillB> {

    List<PraybillB> queryPraybillBsByPraybillPk(String pk_praybill);


    boolean changePraybillBsVbdef(List<PraybillDetail> filtedPraybillDetails);

}
