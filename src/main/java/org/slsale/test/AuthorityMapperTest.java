package org.slsale.test;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.dao.AuthorityMapper;
import org.slsale.pojo.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import java.util.Date;
import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 16:37 2018/4/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class AuthorityMapperTest extends TestCase {
    @Autowired
    private AuthorityMapper mapper;

    private Authority authority=new Authority(100,100,0,new Date(),"admin");
    @Test
    public void testAddAuthority() throws Exception {
        int i = mapper.addAuthority(authority);
        assertEquals(i,1);
    }

    @Test
    public void testDeleteAuthority() throws Exception {
        authority.setId(1707);
        int i = mapper.deleteAuthority(authority);
        assertEquals(i,1);
    }

    @Test
    public void testUpdateAuthority() throws Exception {
        authority.setId(1707);
        authority.setCreatedBy("管理员");
        int i = mapper.updateAuthority(authority);
        assertEquals(i,1);
    }

    @Test
    public void testGetAuthority() throws Exception {
        authority=new Authority();
        authority.setId(1707);
        Authority authority = mapper.getAuthority(this.authority);
        log.warn("authority is:{}",authority);
        assertEquals(authority.getCreatedBy(),"管理员");
    }

    @Test
    public void testGetAuthorities() throws Exception {
        List<Authority> authorities = mapper.getAuthorities();
        log.warn("all authorities are:{}",authorities);
    }

}