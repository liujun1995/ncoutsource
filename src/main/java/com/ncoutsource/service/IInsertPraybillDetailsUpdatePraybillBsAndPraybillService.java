package com.ncoutsource.service;

import com.ncoutsource.entity.Praybill;
import com.ncoutsource.entity.PraybillDetail;

import java.util.List;

public interface IInsertPraybillDetailsUpdatePraybillBsAndPraybillService {


    void insertPraybillDetailsUpdatePraybillBsAndPraybill(List<PraybillDetail> praybillDetails,
                                                          Praybill praybill);


}
