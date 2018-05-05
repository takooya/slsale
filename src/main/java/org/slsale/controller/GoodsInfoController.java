package org.slsale.controller;

import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.pojo.GoodsInfo;
import org.slsale.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/backend/goodsinfolist.html")
    public ModelAndView goodsinfolist(HttpSession session, Model model,
                                      @RequestParam(value = "s_goodsName", required = false) String s_goodsName,
                                      @RequestParam(value = "s_state", required = false) Integer s_state) {
        Object attribute = session.getAttribute(Constants.SESSION_BASE_MODEL);
        Map<String, Object> baseModel = (Map<String, Object>) attribute;
        List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();
        try {
            goodsInfos = goodsInfoService.getGoodsInfoList(s_goodsName, s_state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("goodsInfos", goodsInfos);
        model.addAttribute("s_goodsName", s_goodsName);
        model.addAttribute("s_state", s_state);
        model.addAllAttributes(baseModel);
        return new ModelAndView("backend/goodsinfolist");
    }

    @RequestMapping(value = "/backend/addgoodsinfo.html", method = RequestMethod.POST)
    public String addgoodsinfo(GoodsInfo goodsInfo) {
        log.warn("goodsInfo是{}", goodsInfo);
        goodsInfo.setCreatedBy(getCurrentUser().getLoginCode());
        //todo GoodsFormat
        goodsInfo.setGoodsFormat(null);
        //todo Note
        goodsInfo.setNote(null);
        goodsInfo.setCreateTime(new Date());
        goodsInfo.setLastUpdateTime(new Date());
        try {
            int i = goodsInfoService.addGoodInfo(goodsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/backend/goodsinfolist.html";
    }

    @RequestMapping(value = "/backend/delgoodsinfo.html", method = RequestMethod.POST)
    @ResponseBody
    public String delgoodsinfo(@RequestParam(value = "delId") Integer delId) {
        String resultString = "";
        log.warn("delId{}", delId);
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setId(delId);
        //todo 先判断商品套餐是否引用了该商品,再删除 如果被引用了,返回"noallow"
        if (false) {
            return "noallow";
        }

        int i = 0;
        try {
            i = goodsInfoService.delGoodInfoById(goodsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultString = i > 0 ? "success" : "failed";
        return resultString;
    }
}