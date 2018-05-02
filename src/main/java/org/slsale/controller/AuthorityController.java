package org.slsale.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slsale.common.*;
import org.slsale.pojo.*;
import org.slsale.service.AuthorityService;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.FunctionService;
import org.slsale.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private AuthorityService authorityService;
    @Autowired
    private LoginController loginController;
    @Autowired
    private RedisAPI redis;
    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * 进入到权限管理首页面
     */
    @RequestMapping(value = "/backend/authoritymanage.html", method = RequestMethod.GET)
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

    /**
     * 获取菜单功能列表
     */
    @RequestMapping(value = "/backend/functions.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String functions() {
        String resultString = "";
        Function function = new Function();
        //主菜单的parentId均为0
        function.setId(0);
        List<Function> functions;
        try {
            functions = functionService.getSubFuncList(function);
            List<Menu> menus = new ArrayList<>();
            if (functions != null) {
                for (Function f : functions) {
                    Menu menu = new Menu();
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

    @RequestMapping(value = "/backend/getAuthorityDefault.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String getAuthorityDefault(@RequestParam(value = "rid") Integer rid,
                                      @RequestParam(value = "fid") Integer fid) {
        Authority authority = new Authority();
        authority.setRoleId(rid);
        authority.setFunctionId(fid);
        log.warn("roleId={},functionId={}", rid, fid);
        try {
            authority = authorityService.getAuthorityByRidAndFid(authority);
            if (authority != null && authority.getRoleId() == rid) {
                return "success";
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/backend/modifyAuthority.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyAuthority(HttpSession session
            , @RequestParam(value = "ids") String ids) {
        String resultString = "nodata";
        try {
            if (null != ids) {
                String[] idsArrayStrings = ids.split("-");
                log.warn("idsArrayStrings长度={},idsArrayStrings={}", idsArrayStrings.length, idsArrayStrings);
                if (idsArrayStrings.length > 0) {
                    User user = this.getCurrentUser();
                    //如果修改当前用户的权限,则需更新redis
                    //先删除再添加,需要事务
                    boolean judge = authorityService.hl_addAuthority(idsArrayStrings, user.getLoginCode());
                    List<Menu> menuList = null;
                    menuList = loginController.getFuncByCurrentUser(Integer.valueOf(idsArrayStrings[0]));
                    String menuListJson = JSONObject.toJSONString(menuList);
                    redis.set("menuList" + idsArrayStrings[0], menuListJson);
                    //将用户有权限的url全部放入redis中,用于拦截器拦截请求
                    Authority authority = new Authority();
                    authority.setRoleId(Integer.valueOf(idsArrayStrings[0]));
                    List<Function> functionList = functionService.getFunctionUrlByRoreId(authority);
                    if (functionList != null && functionList.size() >= 0) {
                        StringBuffer sb = new StringBuffer();
                        for (Function f : functionList) {
                            sb.append(f.getFuncUrl());
                        }
                        //将用户有权限的url全部放入redis中,用于拦截器拦截请求
                        redis.set("Role" + idsArrayStrings[0] + "UrlList", sb.toString());
                    }
                    resultString = "success";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
