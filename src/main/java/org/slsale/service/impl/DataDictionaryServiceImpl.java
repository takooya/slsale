package org.slsale.service.impl;

import org.slsale.dao.DataDictionaryMapper;
import org.slsale.pojo.DataDictionary;
import org.slsale.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 19:55 2018/4/13
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary) throws Exception {
        return dataDictionaryMapper.getDataDictionaries(dataDictionary);
    }
}
