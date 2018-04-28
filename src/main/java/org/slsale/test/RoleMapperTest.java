package org.slsale.test;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.dao.RoleMapper;
import org.slsale.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 17:07 2018/4/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class RoleMapperTest extends TestCase {
    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void testGetRoleIdAndNameList() throws Exception {
        List<Role> roles = roleMapper.getRoleIdAndNameList();
        log.warn("roles are:{}",roles);
    }

}