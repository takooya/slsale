package org.slsale.service;

import org.slsale.pojo.Affiche;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:33 2018/5/6
 */
public interface AfficheService {
    List<Affiche> getAfficheList(Affiche affiche)throws Exception;

    Integer count(Affiche affiche)throws Exception;

    int addAffiche(Affiche affiche)throws Exception;

    int delAfficheById(Affiche affiche)throws Exception;

    int checkCode(Affiche affiche)throws Exception;

    int checkTitle(Affiche affiche)throws Exception;

    int checkCodeAndTitle(Affiche affiche)throws Exception;
}
