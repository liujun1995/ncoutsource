package com.ncoutsource.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ncoutsource.entity.Praybill;
import com.ncoutsource.entity.PraybillB;
import com.ncoutsource.entity.PraybillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuJun
 * 入口service
 * 按审批通过，表头bislatest dr=0 vdef14!=Y dr=0的插入,组织71601,
 * 查询出所有符合的表头，
 * 对每一个表头，查询相应的多行表体
 * 多行表体拼出对应的多行detail(负责转换的com.ncoutsource.service.impl.PraybillDetailTransitionServiceImpl
 * 根据表头和多行表体将List表体转换成List detail 并返回)
 * 对所有拼好的detail,为了避免将已审批的数据重复抓取到中间表中，需要先按请购单表体主键查询中间表中是否有该条数据，没有再插入,
 * 所有detail都插入成功后，更新表体vbdef14=Y,表头vdef14=Y
 */


@Service
public class InsertPraybillDetailUpdatePraybillBService {

    @Autowired
    private IStockorgVService iStockorgVService;

    @Autowired
    private IPraybillService iPraybillService;

    @Autowired
    private IPraybillBService iPraybillBService;

    @Autowired
    private IPraybillDetailTransitionService iPraybillDetailTransitionService;

    @Autowired
    private IPraybillDetailService iPraybillDetailService;


    /**
     * 返回
     * @return
     */
    public void insertPraybillDetails(){

        //按code查询库存组织pk
        String orgPk = iStockorgVService.queryStockorgPkByCode("71601");

        //查询出所有符合的表头
        List<Praybill> praybills = iPraybillService.queryPraybillsByCondition("3", "Y", "0", orgPk, "Y");

        for (Praybill praybill : praybills) {

            assemblePraybillDetailsTheninsertPraybillDetailsUpdatePraybillBsAndPraybill(praybill);

        }

    }


    public void assemblePraybillDetailsTheninsertPraybillDetailsUpdatePraybillBsAndPraybill(@NotNull Praybill praybill) {

        List<PraybillB> praybillBS = iPraybillBService.queryPraybillBsByPraybillPk(praybill.getPK_PRAYBILL());

        List<PraybillDetail> praybillDetails = iPraybillDetailTransitionService
                .translateFromPraybillBsToPraybillDetails(praybillBS, praybill);

        insertPraybillDetailsUpdatePraybillBsAndPraybill(praybillDetails,praybill);

    }


    @Transactional
    public void insertPraybillDetailsUpdatePraybillBsAndPraybill(List<PraybillDetail> praybillDetails,
                                                                 Praybill praybill) {

        //对每个praybillDetail,下一次存入中间表时，为了避免将已审批的数据重复抓取到中间表中，
        //需要先按请购单表体主键查询中间表中是否有该条数据，没有再插入
        List<PraybillDetail> filtedPraybillDetails = getFiltedPraybillDetails(praybillDetails);

        if (filtedPraybillDetails.size()>0){

            boolean isInsert = iPraybillDetailService.insertBatch(filtedPraybillDetails,filtedPraybillDetails.size());

            //插入成功后更新po_praybill_b的vbdef14=Y和po_praybill的vdef14=Y
            if (isInsert){

                boolean isChangePraybillBsVbdef = iPraybillBService.changePraybillBsVbdef(filtedPraybillDetails);

                if (isChangePraybillBsVbdef){
                    //更新po_praybill的vdef14=Y
                    String pk_praybill = praybill.getPK_PRAYBILL();

                    boolean isChangePraybillVbdef = iPraybillService.changePraybillVbdef(pk_praybill);

                }
            }
        }
    }



    public List<PraybillDetail> getFiltedPraybillDetails(List<PraybillDetail> praybillDetails) {

        List<PraybillDetail> praybillDetailList = new ArrayList<>();
        //对每个praybillDetail,下一次存入中间表时，为了避免将已审批的数据重复抓取到中间表中，
        //需要先按请购单表体主键查询中间表中是否有该条数据，没有再插入
        for (PraybillDetail praybillDetail : praybillDetails) {

            EntityWrapper<PraybillDetail> praybillDetailEntityWrapper = new EntityWrapper<>();

            praybillDetailEntityWrapper.eq("PK_PRAYBILL_B",praybillDetail.getPK_PRAYBILL_B());

            int count = iPraybillDetailService.selectCount(praybillDetailEntityWrapper);

            if (!(count>0)){
                praybillDetailList.add(praybillDetail);
            }
        }

        return praybillDetailList;

    }

}
