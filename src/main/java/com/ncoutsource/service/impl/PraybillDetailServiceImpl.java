package com.ncoutsource.service.impl;

import java.util.List;
import java.util.UUID;

import com.ncoutsource.mapper.PraybillDetailMapper;
import com.ncoutsource.service.IPraybillDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ncoutsource.entity.PraybillDetail;


@Transactional
@Service
public class PraybillDetailServiceImpl extends ServiceImpl<PraybillDetailMapper, PraybillDetail>
		                               implements IPraybillDetailService {


}
