package com.nc.service.impl;

import com.nc.entity.Praybill;
import com.nc.entity.PraybillDetail;
import com.nc.service.IInsertPraybillDetailsUpdatePraybillBsAndPraybillService;
import com.nc.service.IPraybillBService;
import com.nc.service.IPraybillDetailService;
import com.nc.service.IPraybillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InsertPraybillDetailsUpdatePraybillBsAndPraybillServiceImpl
        implements IInsertPraybillDetailsUpdatePraybillBsAndPraybillService {


    @Autowired
    private IPraybillDetailService iPraybillDetailService;

    @Autowired
    private IPraybillBService iPraybillBService;


    @Autowired
    private IPraybillService iPraybillService;


    /**
     *
     * praybillDetails过滤好的praybillDetails
     * @param praybillDetails
     * @param praybill
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void insertPraybillDetailsUpdatePraybillBsAndPraybill(List<PraybillDetail> praybillDetails,
                                                                 Praybill praybill) {

            boolean isInsert = iPraybillDetailService.insertBatch(praybillDetails,praybillDetails.size());

            //插入成功后更新po_praybill_b的vbdef15=Y和po_praybill的vdef3=Y

            boolean isChangePraybillBsVbdef = iPraybillBService.changePraybillBsVbdef(praybillDetails);

            String pk_praybill = praybill.getPK_PRAYBILL();

            boolean isChangePraybillVbdef = iPraybillService.changePraybillVbdef(pk_praybill);



    }
}
