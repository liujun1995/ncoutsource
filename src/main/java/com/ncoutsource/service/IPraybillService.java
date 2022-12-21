package com.ncoutsource.service;

import com.baomidou.mybatisplus.service.IService;
import com.ncoutsource.entity.Praybill;
import java.util.List;

public interface IPraybillService extends IService<Praybill> {



    /**
     * 审批通过=3,bislatest=Y,dr=0,组织pk_org_v=71601,表头是否抓取vdef14!=Y
     * @return
     * pk_org_v是(org_stockorg_v)的主键
     */
    List<Praybill> queryPraybillsByCondition(String fbillstatus,
                                             String bislatest,
                                             String dr,
                                             String pk_org_v,
                                             String vdef14);

    /**
     * 将po_praybill中的vdef14更新为Y,代表以存入中间表
     * @param pk_praybill
     * @return
     */
    boolean changePraybillVbdef(String pk_praybill);

}
