package org.slsale.pojo;

import lombok.Data;

/**
 * @Author takooya
 * @Description
 * @Date:created in 15:56 2018/4/9
 */
@Data
public class Base {
    private Integer id;
    /**  分页起始行 */
    private Integer startNum;
    /**  页容量 */
    private Integer pageSize;
}
