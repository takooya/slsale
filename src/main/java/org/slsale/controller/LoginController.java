package org.slsale.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.common.RedisAPI;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.Menu;
import org.slsale.pojo.User;
import org.slsale.service.FunctionService;
import org.slsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author takooya
 * @Description
 * @Date:created in 16:21 2018/4/9
 */
@Controller
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private RedisAPI redis;

    @RequestMapping(value = "/main.html", method = RequestMethod.GET)
    public ModelAndView main(HttpSession session) {
        session.setAttribute(Constants.SESSION_BASE_MODEL, null);
        this.setCurrentUser(null);
        User user = this.getCurrentUser();
        //menu list
        List<Menu> menuList = null;
        if (null != user) {
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            //判断redis里是否有数据
            //key:menuList+roleId
            //value:menuList
            if (!redis.exicts("menuList" + user.getRoleId())) {
                //根据当前用户,从数据库,获取菜单列表mList
                menuList = getFuncByCurrentUser(user.getRoleId());
                //json
                if (null != menuList) {
                    String jsonMenuList = JSONArray.toJSONString(menuList);
                    model.put("menuList", jsonMenuList);
                    redis.set("menuList" + user.getRoleId(), jsonMenuList);
                }
            } else {
                String jsonMenuList = redis.get("menuList" + user.getRoleId());
                if (null != jsonMenuList && !("".equals(jsonMenuList))) {
                    model.put("menuList", jsonMenuList);
                } else {
                    return new ModelAndView("redirect:/");
                }
            }
            if (!redis.exicts("Role" + user.getRoleId() + "UrlList")) {
                try {
                    //将用户有权限的url全部放入redis中,用于拦截器拦截请求
                    Authority authority = new Authority();
                    authority.setRoleId(user.getRoleId());
                    List<Function> functionList = functionService.getFunctionUrlByRoreId(authority);
                    if (functionList != null && functionList.size() >= 0) {
                        StringBuffer sb = new StringBuffer();
                        for (Function f : functionList) {
                            sb.append(f.getFuncUrl());
                        }
                        redis.set("Role" + user.getRoleId() + "UrlList", sb.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            session.setAttribute(Constants.SESSION_BASE_MODEL, model);
            return new ModelAndView("main", model);
        }
        return new ModelAndView("redirect:/");
    }

    /**
     * 根据当前角色id获取功能列表(对应的菜单)
     */
    protected List<Menu> getFuncByCurrentUser(Integer roleId) {
        List<Menu> menuList = new ArrayList<>();
        Authority authority = new Authority();
        authority.setRoleId(roleId);
        List<Function> mainFunctionList;
        try {
            mainFunctionList = functionService.getMainFunctionList(authority);
            if (mainFunctionList != null) {
                for (Function function : mainFunctionList) {
                    Menu menu = new Menu();
                    menu.setMainMenu(function);
                    function.setRoleId(roleId);
                    List<Function> subFunctionList = functionService.getSubFunctionList(function);
                    if (subFunctionList != null) {
                        menu.setSubMenu(subFunctionList);
                    }
                    menuList.add(menu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpSession session, @RequestParam String userJson) {
        session.setAttribute(Constants.SESSION_USER, null);
        session.setAttribute(Constants.SESSION_BASE_MODEL, null);
        this.setCurrentUser(null);
        if (userJson == null || "".equals(userJson)) {
            return "nodata";
        } else {
            User user = JSONObject.parseObject(userJson, User.class);
            try {
                if (userService.loginCodeIsExist(user) == 0) {
                    return "nologincode";
                } else {
                    User target = userService.getLoginUser(user);
                    if (target != null) {
                        session.setAttribute(Constants.SESSION_USER, target);
                        //更新当前用户登录的lastLoginTime
                        User updateLoginTimeUser = new User();
                        updateLoginTimeUser.setId(target.getId());
                        updateLoginTimeUser.setLastLoginTime(new Date());
                        userService.modifyUser(updateLoginTimeUser);
                        updateLoginTimeUser = null;
                        return "success";
                    } else {
                        return "pwderror";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }
    }

    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.SESSION_USER);
        session.removeAttribute(Constants.SESSION_BASE_MODEL);
        session.invalidate();
        this.setCurrentUser(null);
        return "index";
    }

    //无权限访问的映射
    @RequestMapping(value = "/401.html")
    public ModelAndView noRole() {
        return new ModelAndView("401");
    }
}
