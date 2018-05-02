package org.slsale.service.impl;

import org.slsale.dao.FunctionMapper;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:21 2018/4/10
 */
@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public List<Function> getMainFunctionList(Authority authority) throws Exception {
        return functionMapper.getMainFunctionList(authority);
    }

    @Override
    public List<Function> getSubFunctionList(Function function) throws Exception {
        return functionMapper.getSubFunctionList(function);
    }

    @Override
    public List<Function> getSubFuncList(Function function) throws Exception {
        return functionMapper.getSubFuncList(function);
    }

    @Override
    public List<Function> getFunctionUrlByRoreId(Authority authority) throws Exception {
        return functionMapper.getFunctionUrlByRoreId(authority);
    }
}
