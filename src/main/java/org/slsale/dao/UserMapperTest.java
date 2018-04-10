package org.slsale.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.common.RedisAPI;
import org.slsale.dao.UserMapper;
import org.slsale.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;
import redis.clients.jedis.Jedis;

import javax.transaction.Transactional;
import java.util.logging.Logger;

/**
 * @Author takooya
 * @Description
 * @Date:created in 21:44 2018/4/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisAPI redis;
    @Test
    public void testGetLoginUser() throws Exception {
        User user=new User();
        user.setLoginCode("admin");
        user.setPassword("123456");
        User target = userMapper.getLoginUser(user);
        log.info("UserMapperTest.testGetLoginUser:getLoginUser:{}",target);
    }

    @Test
    public void testAddUser() throws Exception {
    }
    @Test
    public void testRedis(){
        redis.set("name","lucy");
        String name = redis.get("name");
        log.info(name);
    }
}