package org.slsale.dao;

import org.slsale.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 16:03 2018/4/9
 */
@Repository
public interface UserMapper {
    /**  登录 */
    User getLoginUser(User user)throws Exception;
    /**  判断用户是否存在 */
    int loginCodeIsExist(User user)throws Exception;
    /**  修改用户数据 */
    int modifyUser(User user)throws Exception;
    /**  注册 */
    int addUser(User user)throws Exception;
    /**  获得相应条件的用户数 */
    int count(User user)throws Exception;
    /**  获取用户列表 */
    List<User> getUserList(User user)throws Exception;

    User getUserById(User user)throws Exception;

    Integer deleteUser(User delUser)throws Exception;
}
