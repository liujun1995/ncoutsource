package com.nc.service.impl;


import com.nc.mapper.nc.PraybillDetailMapper;
import com.nc.service.IPraybillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nc.entity.PraybillDetail;

import javax.annotation.Resource;


@Service
public class PraybillDetailServiceImpl extends ServiceImpl<PraybillDetailMapper, PraybillDetail>
		                               implements IPraybillDetailService {

	@Resource
	private PraybillDetailMapper praybillDetailMapper;

	@Override
	public int selectPkPraybillB(String pk_praybill_b) {
		return praybillDetailMapper.selectPkPraybillB(pk_praybill_b);
	}

}
