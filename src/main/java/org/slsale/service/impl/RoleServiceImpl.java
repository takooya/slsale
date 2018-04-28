package org.slsale.service.impl;

import org.slsale.dao.RoleMapper;
import org.slsale.pojo.Role;
import org.slsale.service.RoleService;
import org.slsale.service.UserService;
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
    @Autowired
    private UserService userService;
    @Override
    public List<Role> getRoleList() throws Exception {
        return roleMapper.getRoleList();
    }

    @Override
    public int countRoleByCodeOrName(Role role) throws Exception {
        System.out.println("RoleServiceImpl.countRoleByCodeOrName"+role.toString());
        return roleMapper.countRoleByCodeOrName(role);
    }

    @Override
    public int addRole(Role role) throws Exception {
        return roleMapper.addRole(role);
    }

    @Override
    public int hl_updateRole(Role role) throws Exception {
        //先修改用户表
        int statue = userService.changeRoleName(role);
        //判断roleName是否更改
        //再更改role表
        statue= roleMapper.updateRoleNameAndRoleCode(role);
        return statue;
    }

    @Override
    public int delRole(Role role) throws Exception {
        return roleMapper.delRole(role);
    }

    @Override
    public List<Role> getRoleIdAndNameList() throws Exception {
        return roleMapper.getRoleIdAndNameList();
    }
}
