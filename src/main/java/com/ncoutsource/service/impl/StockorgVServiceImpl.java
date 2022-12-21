package com.ncoutsource.service.impl;

import com.ncoutsource.mapper.StockorgVMapper;
import com.ncoutsource.service.IStockorgVService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


@Transactional
@Service
public class StockorgVServiceImpl implements IStockorgVService {

    @Resource
    private StockorgVMapper stockorgVMapper;

    @Override
    public String queryStockorgPkByCode(String StockorgCode) {

        return stockorgVMapper.selectStockorgPkByCode(StockorgCode);

    }

}
