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
}
