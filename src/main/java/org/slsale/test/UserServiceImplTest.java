package org.slsale.test;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.common.SQLTools;
import org.slsale.pojo.User;
import org.slsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 17:23 2018/4/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class UserServiceImplTest extends TestCase {
    @Autowired
    private UserService userService;
    @Test
    public void testCount() throws Exception {
        String info= SQLTools.transfer("           ");
        log.error("**********:{}","!"+info+"!");
    }
    @Test
    public void testGetUserList() throws Exception {
        User user=new User();
        user.setRoleId(2);
        List<User> userList = userService.getUserList(user);
        for(int i=0;i<userList.size();i++){
            log.warn("userList({})={}",i,userList.get(i).getUserName());
        }
    }
}