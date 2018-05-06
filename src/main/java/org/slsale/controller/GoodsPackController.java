package org.slsale.controller;

import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.pojo.GoodsPack;
import org.slsale.service.GoodsPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author takooya
 * @Description
 * @Date:created in 11:59 2018/5/5
 */
@Controller
@Slf4j
public class GoodsPackController extends BaseController{
    @Autowired
    private GoodsPackService goodsPackService;

    @RequestMapping(value = "/backend/goodspacklist.html")
    public ModelAndView goodspacklist(HttpSession session, Model model,
                                      @RequestParam(value = "s_goodsPackName", required = false) String s_goodsPackName,
                                      @RequestParam(value = "s_typeName", required = false) String s_typeName,
                                      @RequestParam(value = "s_state", required = false) Integer s_state){
        Object attribute = session.getAttribute(Constants.SESSION_BASE_MODEL);
        Map<String, Object> baseModel = (Map<String, Object>) attribute;
        GoodsPack goodsPack=new GoodsPack();
        goodsPack.setGoodsPackName(s_goodsPackName);
        goodsPack.setTypeName(s_typeName);
        goodsPack.setState(s_state);
        log.warn("传入参数:{}",goodsPack);
        List<GoodsPack> goodsPacks= null;
        try {
            goodsPacks = goodsPackService.getGoodsPackList(goodsPack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("goodsPacks",goodsPacks);
        model.addAttribute("s_goodsPackName",s_goodsPackName);
        model.addAttribute("s_typeName",s_typeName);
        model.addAttribute("s_state",s_state);
        model.addAllAttributes(baseModel);
        return new ModelAndView("backend/goodspacklist") ;
    }
    /*@RequestMapping(value = "/backend/addgoodspack.html",method = RequestMethod.POST)
    public String addgoodspack(GoodsPack goodsPack){
        int i=goodsPackService.addGoodsPack(goodsPack);
        return "redirect:/backend/goodspacklist";
    }*/
}
