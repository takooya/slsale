package org.slsale.dao;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 16:03 2018/4/9
 */
@Repository
public interface AuthorityMapper {
    /**  增 */
    int addAuthority(Authority authority)throws Exception;
    /**  删 */
    int deleteAuthority(Authority authority)throws Exception;
    /**  改 */
    int updateAuthority(Authority authority)throws Exception;
    /**  查1 需要id*/
    Authority getAuthority(Authority authority)throws Exception;
    /**  查list */
    List<Authority> getAuthorities()throws Exception;

    Authority getAuthorityByRidAndFid(Authority authority)throws Exception;

    int deleteAuthorityByRoleid(Authority authority)throws Exception;
}
