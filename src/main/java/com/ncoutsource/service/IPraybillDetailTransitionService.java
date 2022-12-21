package com.ncoutsource.service;


import com.ncoutsource.entity.Praybill;
import com.ncoutsource.entity.PraybillB;
import com.ncoutsource.entity.PraybillDetail;

import java.util.List;

public interface IPraybillDetailTransitionService {

    List<PraybillDetail> translateFromPraybillBsToPraybillDetails(List<PraybillB> praybillBs, Praybill praybill);


}
