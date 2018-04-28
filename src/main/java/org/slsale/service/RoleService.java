package org.slsale.service;

import org.slsale.pojo.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 15:57 2018/4/11
 */
public interface RoleService {
    /**  获取角色列表 */
    List<Role> getRoleList()throws Exception;

    int countRoleByCodeOrName(Role role)throws Exception;

    int addRole(Role role)throws Exception;

    int hl_updateRole(Role role)throws Exception;

    int delRole(Role role)throws Exception;

    List<Role> getRoleIdAndNameList()throws Exception;
}
