package com.nc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nc.entity.PraybillB;
import com.nc.entity.PraybillDetail;
import com.nc.mapper.nc.PraybillBMapper;
import com.nc.service.IPraybillBService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class PraybillBServiceImpl extends ServiceImpl<PraybillBMapper, PraybillB>
        implements IPraybillBService {

    /**
     * 根据表头pk查询多个表体
     * @param pk_praybill
     * @return
     */
    @Override
    public List<PraybillB> queryPraybillBsByPraybillPk(String pk_praybill) {
       return baseMapper.selectPraybillBsByPraybillPk(pk_praybill);

    }

    @Override
    @Transactional(value ="ncTransactionManager", propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean changePraybillBsVbdef(List<PraybillDetail> filtedPraybillDetails) {

        Integer integer = baseMapper.updatePraybillBsVbdef(filtedPraybillDetails);

        return integer == filtedPraybillDetails.size();

    }

}
