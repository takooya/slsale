package org.slsale.service.impl;

import org.slsale.dao.GoodsPackMapper;
import org.slsale.pojo.GoodsPack;
import org.slsale.service.GoodsPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 14:17 2018/5/5
 */
@Service
public class GoodsPackServiceImpl implements GoodsPackService {
    @Autowired
    private GoodsPackMapper goodsPackMapper;
    @Override
    public List<GoodsPack> getGoodsPackList(GoodsPack goodsPack) throws Exception {
        return goodsPackMapper.getGoodsPackList(goodsPack);
    }
    @Override
    public int addGoodsPack(GoodsPack goodsPack) throws Exception {
        return goodsPackMapper.addGoodsPack(goodsPack);
    }
}