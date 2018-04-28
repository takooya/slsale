package org.slsale.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 23:39 2018/4/9
 */
@Data
public class Menu {
    //  有两个controller 不同的功能模块,均用到这个bean,
    //在修改此bean时,请注意
    private Function mainMenu;
    private List<Function> subMenu;
}
