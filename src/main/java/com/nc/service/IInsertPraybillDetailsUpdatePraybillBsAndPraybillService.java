package com.nc.service;

import com.nc.entity.Praybill;
import com.nc.entity.PraybillDetail;

import java.util.List;

public interface IInsertPraybillDetailsUpdatePraybillBsAndPraybillService {


    void insertPraybillDetailsUpdatePraybillBsAndPraybill(List<PraybillDetail> praybillDetails,
                                                          Praybill praybill);


}
