package org.slsale.dao.user;

import org.slsale.pojo.user.User;
import org.springframework.stereotype.Repository;

/**
 * @Author takooya
 * @Description
 * @Date:created in 21:33 2018/4/8
 */
@Repository
public interface UserMapper {
    /**  登录 */
    User getLoginUser(User user)throws Exception;
    /**  注册 */
    int addUser(User user)throws Exception;
}
