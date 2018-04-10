package org.slsale.service;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:21 2018/4/10
 */
public interface FunctionService {
    List<Function> getMainFunctionList(Authority authority)throws Exception;
    List<Function> getSubFunctionList(Function function)throws Exception;
}
