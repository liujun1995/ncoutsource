package com.nc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nc.entity.Praybill;
import com.nc.entity.PraybillB;
import com.nc.entity.PraybillDetail;
import com.nc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * @author LiuJun
 * 入口service
 * 请购单表头,不是历史数据vdef1,未写入中间表vdef3
 * 按审批通过，表头bislatest dr=0 vdef1=N dr=0的插入,组织71601,vdef3=N
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


    @Transactional(value = "ncTransactionManager")
    @Override
    public void insertPraybillDetails(){

        //按code查询库存组织pk
        String orgPk = iStockorgVService.queryStockorgPkByCode("71601");
        //查询出所有符合的表头,对于vdef3=N这个条件需要去掉,因为对于行关闭,表头vdef3还是Y这种情况,该行再打开,需要把该行保存到中间表
        //表体过滤掉行关闭的,中间表中已存在的
        //审批通过、最新版本、dr=0、vdef1=N不是历史数据
        List<Praybill> praybills = iPraybillService.queryPraybillsByCondition("3", "Y",
                "0", orgPk, "N");

        if (null!=praybills && praybills.size()>0){

            for (Praybill praybill : praybills) {

                assemblePraybillDetailsTheninsertPraybillDetailsUpdatePraybillBsAndPraybill(praybill);

            }
        }
    }


    public void assemblePraybillDetailsTheninsertPraybillDetailsUpdatePraybillBsAndPraybill(@NotNull Praybill praybill) {

        List<PraybillB> praybillBs = iPraybillBService.queryPraybillBsByPraybillPk(praybill.getPK_PRAYBILL());

        //过滤掉已经存入到中间表的表体行
        List<PraybillB> filtedPraybillBs = getFilteredPraybillBs(praybillBs);

        if (filtedPraybillBs.size()>0){

            List<PraybillDetail> praybillDetails = iPraybillDetailTransitionService
                    .translateFromPraybillBsToPraybillDetails(filtedPraybillBs, praybill);
            //List<PraybillDetail> filtedPraybillDetails = getFilteredPraybillDetails(praybillDetails);
            if (null!=praybillDetails && praybillDetails.size()>0){
                iInsertPraybillDetailsUpdatePraybillBsAndPraybillService.
                        insertPraybillDetailsUpdatePraybillBsAndPraybill(praybillDetails,praybill);
            }
        }
    }


    //过滤掉已经存入到中间表的表体行
    private List<PraybillB> getFilteredPraybillBs(List<PraybillB> praybillBs) {

        List<PraybillB> praybillBList = new ArrayList<>();

        for (PraybillB praybillB : praybillBs) {

            EntityWrapper<PraybillDetail> praybillDetailEntityWrapper = new EntityWrapper<>();

            praybillDetailEntityWrapper.eq("PK_PRAYBILL_B",praybillB.getPK_PRAYBILL_B());

            int count = iPraybillDetailService.selectCount(praybillDetailEntityWrapper);

            if (!(count>0)){
                praybillBList.add(praybillB);
            }
        }
        return praybillBList;
    }

}
