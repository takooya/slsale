package org.slsale.service.impl;

import org.slsale.dao.GoodsInfoMapper;
import org.slsale.pojo.GoodsInfo;
import org.slsale.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 8:52 2018/5/3
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<GoodsInfo> getGoodsInfoList(String goodsName,Integer state) throws Exception {
        return goodsInfoMapper.getGoodsInfoList(goodsName,state);
    }

    @Override
    public int addGoodInfo(GoodsInfo goodsInfo) throws Exception {
        return goodsInfoMapper.addGoodInfo(goodsInfo);
    }

    @Override
    public int delGoodInfoById(GoodsInfo goodsInfo) throws Exception {
        return goodsInfoMapper.delGoodInfoById(goodsInfo);
    }
}
