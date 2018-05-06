package org.slsale.dao;

import org.slsale.pojo.Affiche;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:34 2018/5/6
 */
@Repository
public interface AfficheMapper {
    List<Affiche> getAfficheList(Affiche affiche)throws Exception;

    Integer count(Affiche affiche)throws Exception;

    int addAffiche(Affiche affiche)throws Exception;

    int delAfficheById(Affiche affiche)throws Exception;

    int checkCode(Affiche affiche)throws Exception;

    int checkTitle(Affiche affiche)throws Exception;

    int checkCodeAndTitle(Affiche affiche)throws Exception;
}
