package org.slsale.test;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.common.SQLTools;
import org.slsale.pojo.DataDictionary;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 20:08 2018/4/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"} , loader = GenericXmlContextLoader.class)
@Slf4j
public class DataDictionaryServiceImplTest extends TestCase {
    @Autowired
    private DataDictionaryService service;
    @Test
    public void testGetDataDictionaries() throws Exception {
        DataDictionary data=new DataDictionary();
        data.setTypeCode("CARD_TYPE");
        List<DataDictionary> datas = service.getDataDictionaries(data);
        log.error("获得的list:{}",datas);
    }
}