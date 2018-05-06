package org.slsale.test.forLearn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.pojo.GoodsPack;
import org.slsale.service.GoodsPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author takooya
 * @Description
 * @Date:created in 14:27 2018/5/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mybatis.xml")
@Slf4j
public class GoodsPackServiceTest {
    @Autowired
    private GoodsPackService goodsPackService;
    @Test
    public void getGoodsPackList() throws Exception {
        List<GoodsPack> goodsPackList = goodsPackService.getGoodsPackList(null);
        log.warn("goodsPackList={}",goodsPackList);
    }

    @Test
    public void addGoodsPack() throws Exception {
    }

}