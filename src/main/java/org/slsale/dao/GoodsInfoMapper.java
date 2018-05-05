package org.slsale.dao;

import org.apache.ibatis.annotations.Param;
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
    List<GoodsInfo> getGoodsInfoList(
            @Param(value = "goodsName") String goodsName,
            @Param(value = "state") Integer state) throws Exception;

    int addGoodInfo(GoodsInfo goodsInfo)throws Exception;

    int delGoodInfoById(GoodsInfo goodsInfo)throws Exception;
}
