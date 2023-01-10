package com.nc.service.impl;

import com.nc.mapper.StockorgVMapper;
import com.nc.service.IStockorgVService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class StockorgVServiceImpl implements IStockorgVService {

    @Resource
    private StockorgVMapper stockorgVMapper;

    @Override
    public String queryStockorgPkByCode(String StockorgCode) {

        return stockorgVMapper.selectStockorgPkByCode(StockorgCode);

    }

}
