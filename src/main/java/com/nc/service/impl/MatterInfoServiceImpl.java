package com.nc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nc.entity.wxwb.MatterInfo;
import com.nc.mapper.wxwb.MatterInfoMapper;
import com.nc.service.IMatterInfoService;

import javax.annotation.Resource;

public class MatterInfoServiceImpl extends ServiceImpl<MatterInfoMapper, MatterInfo> implements IMatterInfoService {

    @Resource
    private MatterInfoMapper matterInfoMapper;


}
