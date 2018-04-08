package org.slsale.dao.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import javax.transaction.Transactional;
import java.util.logging.Logger;

/**
 * @Author takooya
 * @Description
 * @Date:created in 21:44 2018/4/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetLoginUser() throws Exception {
        User user=new User();
        user.setLoginCode("admin");
        user.setPassword("123456");
        User target = userMapper.getLoginUser(user);
        System.out.println("UserMapperTest.testGetLoginUser:getLoginUser:"+target);
    }

    @Test
    public void testAddUser() throws Exception {
    }

}