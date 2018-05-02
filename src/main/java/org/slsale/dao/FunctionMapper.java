package org.slsale.dao;

import org.apache.ibatis.annotations.Param;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 9:04 2018/4/10
 */
@Repository
public interface FunctionMapper {
    List<Function> getMainFunctionList(Authority authority)throws Exception;
    List<Function> getSubFunctionList(Function function)throws Exception;
    List<Function> getSubFuncList(Function function)throws Exception;
    List<Function> getFuncListByIn(@Param(value = "idsSqlString") String idsSqlString)throws Exception;
    List<Function> getFunctionUrlByRoreId(Authority authority)throws Exception;
}
