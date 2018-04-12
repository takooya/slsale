package org.slsale.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.tools.javac.util.ArrayUtils;
import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.common.PageSupport;
import org.slsale.common.RedisAPI;
import org.slsale.common.SQLTools;
import org.slsale.pojo.Menu;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author takooya
 * @Description
 * @Date:created in 20:57 2018/4/10
 */
@Controller
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisAPI redis;

    @RequestMapping(value = "/backend/modify.html", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(HttpSession session, @RequestParam String info) {
        if (null == info || "".equals(info)) {
            return "nodata";
        }
        JSONObject jsonObject = JSONObject.parseObject(info);
        String oldPassword = (String) jsonObject.get("old");
        String newPassword = (String) jsonObject.get("new");
        String confirm = (String) jsonObject.get("confirm");
        if (!newPassword.equals(confirm)) {
            return "failed";
        }
        try {
            Object targetObject = session.getAttribute(Constants.SESSION_USER);
            User targetUser = null;
            if (targetObject == null) {
                return "nologincode";
            } else if (!(targetObject instanceof User)) {
                return "failed";
            } else {
                targetUser = (User) targetObject;
            }
            targetUser.setPassword(oldPassword);
            User confirmUser = userService.getLoginUser(targetUser);
            if (null == confirmUser) {
                return "pwderror";
            }
            confirmUser.setPassword(newPassword);
            confirmUser.setLastUpdateTime(new Date());
            userService.modifyUser(confirmUser);
            session.setAttribute(Constants.SESSION_USER, confirmUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }


    @RequestMapping(value = "/backend/userlist.html")
    public ModelAndView userlist(Model model, HttpSession session,
                                 @RequestParam(value = "s_loginCode", required = false) String s_loginCode,
                                 @RequestParam(value = "s_referCode", required = false) String s_referCode,
                                 @RequestParam(value = "s_rodeId", required = false) Integer s_rodeId,
                                 @RequestParam(value = "s_isStart", required = false) Integer s_isStart,
                                 @RequestParam(value = "currentpage",required = false) Integer currentpage) {
        Map<String, Object> baseModel = (Map<String, Object>) session.getAttribute(Constants.SESSION_BASE_MODEL);
        log.error("前台传来了数据如下:\ns_loginCode={}\ns_referCode={}\ns_rodeId={}\ns_isStart={}", s_loginCode, s_referCode, s_rodeId, s_isStart);
        if (model == null) {
            return new ModelAndView("redirect:/");
        } else {
            //获取roleList
            List<Role> roleList = null;
            try {
                if (!redis.exicts("roleList")) {
                    roleList = roleService.getRoleList();
                    redis.set("roleList", JSONObject.toJSONString(roleList));
                    log.error("List<Role>的roleList转换为JSON格式:{}", redis.get("roleList"));
                } else {
                    try {
                        roleList = (List<Role>) JSONObject.parse(redis.get("roleList"));
                        log.error("JSON格式的roleList转换为List<Role>:{}", roleList);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("roleList转换异常:{}", e.getMessage());
                        roleList = roleService.getRoleList();
                        redis.set("roleList", JSONObject.toJSONString(roleList));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //查询用户功能
            User user = new User();
            if (null != s_loginCode && !"".equals(s_loginCode)) {
                log.error("s_loginCode:{}\n防注入:{}", s_loginCode, "%" + SQLTools.transfer(s_loginCode) + "%");
                user.setLoginCode("%" + SQLTools.transfer(s_loginCode) + "%");
            }
            if (null != s_referCode && !"".equals(s_referCode)) {
                user.setReferCode("%" + SQLTools.transfer(s_referCode) + "%");
            }
            if (null != s_rodeId) {
                //start获取roleroleList中的idList
                Integer[] roleIds = new Integer[roleList.size()];
                for (int i = 0; i < roleIds.length; i++) {
                    roleIds[i] = roleList.get(i).getId();
                }
                //end获取roleroleList中的idList
                if (Arrays.binarySearch(roleIds, s_rodeId) >= 0) {
                    user.setRoleId(s_rodeId);
                }
            }
            if (null != s_isStart && Arrays.binarySearch(new Integer[]{1, 2}, s_isStart) >= 0) {
                user.setIsStart(s_isStart);
            }
            log.error("过滤后的user为:{}", user);
            //分页功能
            PageSupport page = new PageSupport();
            try {
                page.setTotalCount(userService.count(user));
            } catch (Exception e) {
                e.printStackTrace();
                page.setTotalCount(0);
            }
            if (page.getTotalCount() > 0) {
                if (currentpage != null) {
                    page.setPage(currentpage);
                }else {
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
            }
            //mysql--分页查询limit?(起始下标:(当前页-1)*页容量),?(页容量)
            user.setStartNum((page.getPage() - 1) * page.getPageSize());
            user.setPageSize(page.getPageSize());
            List<User> userList = null;
            try {
                userList = userService.getUserList(user);
            } catch (Exception e) {
                e.printStackTrace();
                userList = null;
                if (page == null) {
                    page = new PageSupport();
                    page.setItems(null);
                }
            }
            page.setItems(userList);
            model.addAllAttributes(baseModel);
            model.addAttribute("roleList",roleList);
            model.addAttribute("page",page);
            model.addAttribute("s_loginCode",s_loginCode);
            model.addAttribute("s_referCode",s_referCode);
            model.addAttribute("s_isStart",s_isStart);
            model.addAttribute("s_rodeId",s_rodeId);
            return new ModelAndView("backend/userlist");
        }
    }
}