package org.slsale.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.pojo.Affiche;
import org.slsale.service.AfficheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:38 2018/5/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mybatis.xml")
@Slf4j
public class AfficheServiceTest {
    @Autowired
    private AfficheService afficheService;
    @Test
    public void getAfficheListTest() throws Exception {
        log.warn("result={}",afficheService.getAfficheList(new Affiche()));
    }
}