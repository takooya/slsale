package org.slsale.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slsale.common.*;
import org.slsale.pojo.Function;
import org.slsale.pojo.Menu;
import org.slsale.pojo.Role;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.FunctionService;
import org.slsale.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author takooya
 * @Description
 * @Date:created in 20:57 2018/4/10
 */
@Controller
@Slf4j
public class AuthorityController extends BaseController {

    @Autowired
    private FunctionService functionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisAPI redis;
    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * 进入到权限管理首页面
     */
    @RequestMapping(value = "/backend/authoritymanage.html",method = RequestMethod.GET)
    public ModelAndView authoritymanage(HttpSession session, Model model) {
        Map<String, Object> baseModel = (Map<String, Object>) session.getAttribute(Constants.SESSION_BASE_MODEL);
        if (baseModel == null) {
            return new ModelAndView("redirect:/");
        } else {
            List<Role> roleList = null;
            try {
                roleList = roleService.getRoleIdAndNameList();
            } catch (Exception e) {
                e.printStackTrace();
                roleList = null;
            }
            model.addAllAttributes(baseModel);
            model.addAttribute(roleList);
            return new ModelAndView("/backend/authoritymanage");
        }
    }

    /** 获取菜单功能列表 */
    @RequestMapping(value = "/backend/functions.html",produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public String functions(){
        String resultString="";
        Function function=new Function();
        //主菜单的parentId均为0
        function.setId(0);
        List<Function> functions;
        try {
            functions=functionService.getSubFuncList(function);
            List<Menu> menus=new ArrayList<>();
            if(functions!=null){
                for (Function f:functions){
                    Menu menu=new Menu();
                    menu.setMainMenu(f);
                    menu.setSubMenu(functionService.getSubFuncList(f));
                    menus.add(menu);
                }
                resultString = JSONObject.toJSONString(menus);
                log.warn(resultString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
