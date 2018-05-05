package org.slsale.service;

import org.slsale.pojo.GoodsInfo;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 8:52 2018/5/3
 */
public interface GoodsInfoService {
    List<GoodsInfo> getGoodsInfoList(String goodsName,Integer state)throws Exception;

    int addGoodInfo(GoodsInfo goodsInfo)throws Exception;

    int delGoodInfoById(GoodsInfo goodsInfo)throws Exception;
}
