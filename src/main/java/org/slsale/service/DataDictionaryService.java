package org.slsale.service;

import org.slsale.pojo.DataDictionary;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 19:54 2018/4/13
 */
public interface DataDictionaryService {
    List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary)throws Exception;
}
