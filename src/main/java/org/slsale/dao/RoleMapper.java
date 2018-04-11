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
    /**  获取角色列表 */
    List<Role> getRoleList()throws Exception;
}
