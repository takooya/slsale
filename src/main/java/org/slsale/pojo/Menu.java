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
    private Function mainMenu;
    private List<Function> subMenu;
}
