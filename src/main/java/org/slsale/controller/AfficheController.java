package org.slsale.controller;

import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.common.PageSupport;
import org.slsale.pojo.Affiche;
import org.slsale.service.AfficheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author takooya
 * @Description
 * @Date:created in 11:59 2018/5/5
 */
@Controller
@Slf4j
public class AfficheController extends BaseController {
    @Autowired
    private AfficheService afficheService;

    @RequestMapping(value = "/informanage/affiche.html", method = RequestMethod.GET)
    public ModelAndView goodspacklist(HttpSession session, Model model,
                                      @RequestParam(value = "currentpage", required = false) Integer currentpage) {
        Object attribute = session.getAttribute(Constants.SESSION_BASE_MODEL);
        Map<String, Object> baseModel = (Map<String, Object>) attribute;
        Affiche affiche = new Affiche();
        List<Affiche> affiches = null;
        PageSupport page = new PageSupport();
        try {
            page.setTotalCount(afficheService.count(affiche));
        } catch (Exception e) {
            e.printStackTrace();
            page.setTotalCount(0);
        }
        if (page.getTotalCount() > 0) {
            if (currentpage != null) {
                page.setPage(currentpage);
            } else {
                page.setPage(1);
            }
            if (page.getPage() <= 0) {
                page.setPage(1);
            }
            if (page.getPage() > page.getPageCount()) {
                page.setPage(page.getPageCount());
            }
        } else {
            page.setItems(null);
            page.setPage(1);
        }
        //mysql--分页查询limit?(起始下标:(当前页-1)*页容量),?(页容量)
        affiche.setStartNum((page.getPage() - 1) * page.getPageSize());
        affiche.setPageSize(page.getPageSize());
        try {
            affiches = afficheService.getAfficheList(affiche);
        } catch (Exception e) {
            e.printStackTrace();
            affiches = null;
            if (page == null) {
                page = new PageSupport();
                page.setItems(null);
            }
        }
        page.setItems(affiches);
        model.addAttribute("page", page);
        model.addAllAttributes(baseModel);
        return new ModelAndView("informanage/affiche");
    }

    @RequestMapping(value = "/informanage/addaffiche.html", method = RequestMethod.POST)
    public String addAffiche(Affiche affiche) {
        try {
            if (afficheService.checkCodeAndTitle(affiche) > 0) {
                return "redirect:/";
            }
            log.warn("get affiche form from is {}", affiche);
            affiche.setPublisher(getCurrentUser().getLoginCode());
            affiche.setPublishTime(new Date());
            int i = afficheService.addAffiche(affiche);
            log.warn("AfficheController.addAffiche={}", i);
        } catch (Exception e) {
            log.warn("AfficheController.addAffiche 失败");
            e.printStackTrace();
        }
        return "redirect:/informanage/affiche.html";
    }

    @RequestMapping(value = "/informanage/delAffiche.html", method = RequestMethod.POST)
    @ResponseBody
    public String delAffiche(@RequestParam("d_id") Integer d_id) {
        log.warn("get affiche form from is {}", d_id);
        try {
            Affiche affiche = new Affiche();
            affiche.setId(d_id);
            int i = afficheService.delAfficheById(affiche);
            log.warn("AfficheController.delAfficheById={}", i);
            if (i > 0) {
                return "success";
            } else {
                return "nodata";
            }
        } catch (Exception e) {
            log.warn("AfficheController.delAffiche 失败");
            e.printStackTrace();
            return "failed";
        }
    }

    @RequestMapping(value = "/backend/checkcode.html", method = RequestMethod.POST)
    @ResponseBody
    public String checkcode(@RequestParam("code") String code) {
        if (code == null || code.trim() == "") {
            return "success";
        }
        Affiche affiche = new Affiche();
        affiche.setCode(code.trim());
        try {
            if (afficheService.checkCode(affiche) == 0) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "failed";
    }

    @RequestMapping(value = "/backend/checktitle.html", method = RequestMethod.POST)
    @ResponseBody
    public String checktitle(@RequestParam("title") String title) {
        if (title == null || title.trim() == "") {
            return "success";
        }
        Affiche affiche = new Affiche();
        affiche.setTitle(title.trim());
        try {
            if (afficheService.checkTitle(affiche) == 0) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "failed";
    }
}
