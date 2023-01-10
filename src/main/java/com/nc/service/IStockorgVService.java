package com.nc.service;


public interface IStockorgVService {

    /**
     * 按code查询出请购单库存组织的主键
     * @param StockorgCode
     * @return
     */

    String queryStockorgPkByCode(String StockorgCode);

}
