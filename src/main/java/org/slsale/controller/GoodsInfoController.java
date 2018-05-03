package org.slsale.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.pojo.GoodsInfo;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.slsale.service.GoodsInfoService;
import org.slsale.service.RoleService;
import org.slsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author takooya
 * @Description
 * @Date:created in 19:45 2018/4/16
 */
@Controller
@Slf4j
public class GoodsInfoController extends BaseController {
    @Autowired
    private GoodsInfoService goodsInfoService;

    @RequestMapping(value = "/backend/goodsinfolist.html",method = RequestMethod.GET)
    public ModelAndView goodsinfolist(HttpSession session, Model model){
        Object attribute = session.getAttribute(Constants.SESSION_BASE_MODEL);
        Map<String, Object> baseModel = (Map<String, Object>) attribute;
        List<GoodsInfo> goodsInfos= new ArrayList<GoodsInfo>();
        try {
            goodsInfos = goodsInfoService.getGoodsInfoList();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("goodsInfos",goodsInfos);
        model.addAllAttributes(baseModel);
        return new ModelAndView("backend/goodsinfolist");
    }

}
