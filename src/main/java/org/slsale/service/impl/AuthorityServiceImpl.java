package org.slsale.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slsale.dao.AuthorityMapper;
import org.slsale.dao.FunctionMapper;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:56 2018/4/30
 */
@Service
@Slf4j
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private FunctionMapper functionMapper;

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

    @Override
    public boolean hl_addAuthority(String[] ids, String createdBy) throws Exception {
        Authority authority = new Authority();
        authority.setRoleId(Integer.parseInt(ids[0]));
        //删除角色下的权限
        authorityMapper.deleteAuthorityByRoleid(authority);
        String idsSqlString = "";
        for (int i = 1; i < ids.length; i++) {
            idsSqlString += Integer.parseInt(ids[i]) + ",";
        }
        if(idsSqlString!=null&&idsSqlString.contains(",")){
            idsSqlString=idsSqlString.substring(0,idsSqlString.lastIndexOf(","));
            log.warn("idsSqlString={}",idsSqlString);
            List<Function> functions=functionMapper.getFuncListByIn(idsSqlString);
            log.warn("functions={}",functions);
            if(functions!=null&&functions.size()>0){
                for (Function f:functions){
                    authority.setFunctionId(f.getId());
                    authority.setCreatedBy(createdBy);
                    authority.setCreationTime(new Date());
                    log.warn("authority={}",authority);
                    authorityMapper.addAuthority(authority);
                }
            }
        }
        return true;
    }
}
