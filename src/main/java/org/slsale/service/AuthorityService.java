package org.slsale.service;

import org.slsale.pojo.Authority;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:54 2018/4/30
 */
public interface AuthorityService {
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

    boolean hl_addAuthority(String[] idsArrayStrings, String userName) throws Exception;
}
