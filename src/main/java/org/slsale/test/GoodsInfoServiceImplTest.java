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
    public void getGoodsInfoList() throws Exception {
        List<GoodsInfo> goodsInfoList = goodsInfoService.getGoodsInfoList();
        log.warn("goodsInfoList={}",goodsInfoList);
    }

}