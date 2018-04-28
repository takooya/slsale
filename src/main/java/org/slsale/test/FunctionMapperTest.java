package org.slsale.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.dao.FunctionMapper;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 21:44 2018/4/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class FunctionMapperTest {
    @Autowired
    private FunctionMapper functionMapper;

    @Test
    public void getMainFunctionListTest() throws Exception {
        Authority authority=new Authority();
        authority.setRoleId(1);
        List<Function> mainFunctionList = functionMapper.getMainFunctionList(authority);
        log.error("getMainFunctionListTest():{}",mainFunctionList);
    }
    @Test
    public void getSubFunctionListTest() throws Exception {
        Function function=new Function();
        function.setRoleId(1);
        function.setId(1);
        List<Function> subFunctionList = functionMapper.getSubFunctionList(function);
        log.error("getSubFunctionListTest():{}",subFunctionList);
    }

    @Test
    public void getSubFuncListTest() throws Exception {
        Function function=new Function();
        function.setId(0);
        List<Function> subFunctionList = functionMapper.getSubFuncList(function);
        log.error("getSubFuncListTest():{}",subFunctionList);
    }

}