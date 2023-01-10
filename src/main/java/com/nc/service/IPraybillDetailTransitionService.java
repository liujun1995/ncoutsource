package com.nc.service;


import com.nc.entity.Praybill;
import com.nc.entity.PraybillB;
import com.nc.entity.PraybillDetail;

import java.util.List;

public interface IPraybillDetailTransitionService {

    List<PraybillDetail> translateFromPraybillBsToPraybillDetails(List<PraybillB> praybillBs, Praybill praybill);


}
