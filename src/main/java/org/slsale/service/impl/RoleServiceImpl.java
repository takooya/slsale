package org.slsale.service.impl;

import org.slsale.dao.RoleMapper;
import org.slsale.pojo.Role;
import org.slsale.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 15:58 2018/4/11
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleList() throws Exception {
        return roleMapper.getRoleList();
    }
}
