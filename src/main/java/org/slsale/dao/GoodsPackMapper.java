package org.slsale.dao;

import org.slsale.pojo.GoodsPack;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 14:19 2018/5/5
 */
@Repository
public interface GoodsPackMapper {
    List<GoodsPack> getGoodsPackList(GoodsPack goodsPack)throws Exception;
    int addGoodsPack(GoodsPack goodsPack)throws Exception;
}
