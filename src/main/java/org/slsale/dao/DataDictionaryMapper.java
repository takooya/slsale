package org.slsale.dao;

import org.slsale.pojo.DataDictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 19:56 2018/4/13
 */
@Repository
public interface DataDictionaryMapper {
    List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary)throws Exception;
}
