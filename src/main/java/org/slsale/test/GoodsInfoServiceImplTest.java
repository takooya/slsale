package org.slsale.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.pojo.GoodsInfo;
import org.slsale.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:06 2018/5/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mybatis.xml")
@Slf4j
public class GoodsInfoServiceImplTest {
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Test
    public void getGoodsInfoListTest() throws Exception {
        List<GoodsInfo> goodsInfoList = goodsInfoService.getGoodsInfoList("上衣",null);
        log.warn("goodsInfoList={}",goodsInfoList);
    }
    @Test
    public void addGoodInfoTest() throws Exception {
        GoodsInfo goodsInfo=new GoodsInfo();
        goodsInfo.setLastUpdateTime(new Date());
        goodsInfo.setGoodsName("admin");
        goodsInfo.setGoodsSn("admin");
        goodsInfo.setMarketPrice(100);
        goodsInfo.setRealPrice(100);
        goodsInfo.setState(2);
        goodsInfo.setNum(10);
        goodsInfo.setUnit("admin");
        int i = goodsInfoService.addGoodInfo(goodsInfo);
        log.warn("insert了{}数据",i);
    }
    @Test
    public void delGoodInfoByIdTest() throws Exception {
        GoodsInfo goodsInfo=new GoodsInfo();
        goodsInfo.setId(27);
        int i = goodsInfoService.delGoodInfoById(goodsInfo);
        log.warn("delete了{}数据",i);
    }
}