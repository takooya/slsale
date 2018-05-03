package org.slsale.dao;

import org.slsale.pojo.GoodsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 8:58 2018/5/3
 */
@Repository
public interface GoodsInfoMapper {
    List<GoodsInfo> getGoodsInfoList()throws Exception;
}
