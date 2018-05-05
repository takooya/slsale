package org.slsale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author takooya
 * @Description
 * @Date:created in 11:59 2018/5/5
 */
@Controller
public class GoodsPackController {
    @RequestMapping(value = "/backend/goodspacklist.html",method = RequestMethod.GET)
    public String goodspacklist(){
        return "backend/goodspacklist";
    }
}
