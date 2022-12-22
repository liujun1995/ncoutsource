package com.ncoutsource.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ncoutsource.entity.Praybill;
import com.ncoutsource.entity.PraybillB;
import com.ncoutsource.entity.PraybillDetail;
import com.ncoutsource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * @author LiuJun
 * 入口service
 * 不是历史数据vdef15,未写入中间表vdef14
 * 按审批通过，表头bislatest dr=0 vdef14=N dr=0的插入,组织71601,vdef15=N
 * 查询出所有符合的表头，
 * 对每一个表头，查询相应的多行表体
 * 多行表体拼出对应的多行detail(负责转换的com.ncoutsource.service.impl.PraybillDetailTransitionServiceImpl
 * 根据表头和多行表体将List表体转换成List detail 并返回)
 * 对所有拼好的detail,为了避免将已审批的数据重复抓取到中间表中，需要先按请购单表体主键查询中间表中是否有该条数据，没有再插入,
 * 所有detail都插入成功后，更新表体vbdef14=Y,表头vdef14=Y
 * 请购单关闭了的不抓取到中间表
 */


@Service
public class GainInsertPraybillDetailsServiceImpl implements IGainInsertPraybillDetailsService {

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

    @Autowired
    private IInsertPraybillDetailsUpdatePraybillBsAndPraybillService
            iInsertPraybillDetailsUpdatePraybillBsAndPraybillService;


    @Override
    public void insertPraybillDetails(){

        //按code查询库存组织pk
        String orgPk = iStockorgVService.queryStockorgPkByCode("71601");

        //查询出所有符合的表头
        //审批通过、最新版本、dr=0、vdef14=N没存入中间表、vdef15=N不是历史数据
        List<Praybill> praybills = iPraybillService.queryPraybillsByCondition("3", "Y",
                "0", orgPk, "N","N");

        if (null!=praybills && praybills.size()>0){

            for (Praybill praybill : praybills) {

                assemblePraybillDetailsTheninsertPraybillDetailsUpdatePraybillBsAndPraybill(praybill);

            }

        }

    }


    public void assemblePraybillDetailsTheninsertPraybillDetailsUpdatePraybillBsAndPraybill(@NotNull Praybill praybill) {

        List<PraybillB> praybillBS = iPraybillBService.queryPraybillBsByPraybillPk(praybill.getPK_PRAYBILL());

        List<PraybillDetail> praybillDetails = iPraybillDetailTransitionService
                .translateFromPraybillBsToPraybillDetails(praybillBS, praybill);

        List<PraybillDetail> filtedPraybillDetails = getFiltedPraybillDetails(praybillDetails);


        //调用IInsertPraybillDetailsUpdatePraybillBsAndPraybillService
        if (null!=filtedPraybillDetails && filtedPraybillDetails.size()>0){
            iInsertPraybillDetailsUpdatePraybillBsAndPraybillService.
                    insertPraybillDetailsUpdatePraybillBsAndPraybill(filtedPraybillDetails,praybill);
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
