package org.slsale.dao;

import org.slsale.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 15:54 2018/4/11
 */
@Repository
public interface RoleMapper {
    /**
     * 获取角色列表
     */
    List<Role> getRoleList() throws Exception;

    int countRoleByCodeOrName(Role role) throws Exception;

    int addRole(Role role) throws Exception;

    int updateRoleNameAndRoleCode(Role role) throws Exception;

    int delRole(Role role) throws Exception;

    List<Role> getRoleIdAndNameList()throws Exception;
}
