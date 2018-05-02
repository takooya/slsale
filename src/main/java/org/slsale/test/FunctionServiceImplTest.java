package org.slsale.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:17 2018/5/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class FunctionServiceImplTest {
    @Autowired
    private FunctionService service;

    @Test
    public void getFunctionUrlByRoreId() throws Exception {
        Authority authority=new Authority();
        authority.setRoleId(2);
        List<Function> funcs = service.getFunctionUrlByRoreId(authority);
        Assert.assertEquals(funcs.size(),29);
    }

}