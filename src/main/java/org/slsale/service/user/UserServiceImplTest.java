package org.slsale.service.user;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * @Author takooya
 * @Description
 * @Date:created in 22:31 2018/4/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void testGetLoginUser() throws Exception {
        User user=new User();
        user.setLoginCode("admin");
        user.setPassword("123456");
        User target = userService.getLoginUser(user);
        log.info("UserMapperTest.testGetLoginUser:getLoginUser:{}",target);
    }

    public void testAddUser() throws Exception {
    }
}