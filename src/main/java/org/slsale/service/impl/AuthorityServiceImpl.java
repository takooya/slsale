package org.slsale.service.impl;

import org.slsale.dao.AuthorityMapper;
import org.slsale.pojo.Authority;
import org.slsale.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:56 2018/4/30
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;
    @Override
    public int addAuthority(Authority authority) throws Exception {
        return authorityMapper.addAuthority(authority);
    }

    @Override
    public int deleteAuthority(Authority authority) throws Exception {
        return authorityMapper.deleteAuthority(authority);
    }

    @Override
    public int updateAuthority(Authority authority) throws Exception {
        return authorityMapper.updateAuthority(authority);
    }

    @Override
    public Authority getAuthority(Authority authority) throws Exception {
        return authorityMapper.getAuthority(authority);
    }

    @Override
    public List<Authority> getAuthorities() throws Exception {
        return authorityMapper.getAuthorities();
    }

    @Override
    public Authority getAuthorityByRidAndFid(Authority authority) throws Exception {
        return authorityMapper.getAuthorityByRidAndFid(authority);
    }
}
