package org.slsale.service;

import org.slsale.pojo.GoodsPack;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 14:08 2018/5/5
 */
public interface GoodsPackService {
    List<GoodsPack> getGoodsPackList(GoodsPack goodsPack)throws Exception;
    int addGoodsPack(GoodsPack goodsPack)throws Exception;
}
