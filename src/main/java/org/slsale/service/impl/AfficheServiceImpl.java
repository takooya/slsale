package org.slsale.service.impl;

import org.slsale.dao.AfficheMapper;
import org.slsale.pojo.Affiche;
import org.slsale.service.AfficheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:33 2018/5/6
 */
@Service
public class AfficheServiceImpl implements AfficheService {
    @Autowired
    private AfficheMapper afficheMapper;
    @Override
    public List<Affiche> getAfficheList(Affiche affiche) throws Exception {
        return afficheMapper.getAfficheList(affiche);
    }

    @Override
    public Integer count(Affiche affiche) throws Exception {
        return afficheMapper.count(affiche);
    }

    @Override
    public int addAffiche(Affiche affiche) throws Exception {
        return afficheMapper.addAffiche(affiche);
    }

    @Override
    public int delAfficheById(Affiche affiche) throws Exception {
        return afficheMapper.delAfficheById(affiche);
    }

    @Override
    public int checkCode(Affiche affiche) throws Exception {
        return afficheMapper.checkCode(affiche);
    }

    @Override
    public int checkTitle(Affiche affiche) throws Exception {
        return afficheMapper.checkTitle(affiche);
    }

    @Override
    public int checkCodeAndTitle(Affiche affiche) throws Exception {
        return afficheMapper.checkCodeAndTitle(affiche);
    }
}
