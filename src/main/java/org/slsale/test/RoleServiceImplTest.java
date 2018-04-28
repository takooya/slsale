package org.slsale.test;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.pojo.Role;
import org.slsale.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * @Author takooya
 * @Description
 * @Date:created in 13:19 2018/4/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class RoleServiceImplTest extends TestCase {
    @Autowired
    private RoleService roleService;
    @Test
    public void testCountRoleByCodeOrName() throws Exception {
        Role role=new Role();
        role.setRoleName("会员");
        role.setRoleCode("sl_role01");
        int i = roleService.countRoleByCodeOrName(role);
        Assert.assertEquals(i,2);
    }

    @Test
    public void testUpdateRoleNameAndRoleCode() throws Exception{
        Role role=new Role();
        role.setId(28);
        role.setRoleName("hahaha");
        role.setRoleCode("hahaha");
        int i = roleService.hl_updateRole(role);
        Assert.assertEquals(i,1);
    }
}