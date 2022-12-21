package com.ncoutsource.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ncoutsource.entity.Praybill;
import com.ncoutsource.mapper.PraybillMapper;
import com.ncoutsource.service.IPraybillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class PraybillServiceImpl extends ServiceImpl<PraybillMapper, Praybill>
                                 implements IPraybillService {

    /**
     * 根据查询条件查询多个请购单表头
     * @param fbillstatus
     * @param bislatest
     * @param dr
     * @param pk_org_v
     * @param vdef14
     * @return
     */
    @Override
    public List<Praybill> queryPraybillsByCondition(String fbillstatus,
                                                    String bislatest,
                                                    String dr,
                                                    String pk_org_v,
                                                    String vdef14) {

        return baseMapper.selectPraybillsByCondition(fbillstatus,bislatest, dr, pk_org_v, vdef14);
    }


    @Override
    @Transactional
    public boolean changePraybillVbdef(String pk_praybill) {

        Integer integer = baseMapper.updatePraybillVbdef(pk_praybill);

        return integer==1;

    }

}
