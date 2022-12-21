package com.ncoutsource.service;

import com.baomidou.mybatisplus.service.IService;
import com.ncoutsource.entity.PraybillB;
import com.ncoutsource.entity.PraybillDetail;

import java.util.List;


public interface IPraybillBService extends IService<PraybillB> {

    List<PraybillB> queryPraybillBsByPraybillPk(String pk_praybill);


    boolean changePraybillBsVbdef(List<PraybillDetail> filtedPraybillDetails);

}
