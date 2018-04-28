package org.slsale.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.dao.UserMapper;
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
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/backend/rolelist.html", method = RequestMethod.GET)
    public ModelAndView rolelist(HttpSession session, Model model) {
        Object attribute = session.getAttribute(Constants.SESSION_BASE_MODEL);
        Map<String, Object> baseModel = (Map<String, Object>) attribute;
        List<Role> roleList = null;
        try {
            roleList = roleService.getRoleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("roleList", roleList);
        model.addAllAttributes(baseModel);
        return new ModelAndView("backend/rolelist");
    }

    @RequestMapping(value = "/backend/addRole.html", method = RequestMethod.POST)
    @ResponseBody
    public String addRole(@RequestParam(value = "role") String roleJson, HttpSession session) {
        if (roleJson == null || roleJson.trim().equals("")) {
            return "nodata";
        }
        Role role = new Role();
        try {
            JSONObject jsonObject = JSONObject.parseObject(roleJson);
            String roleCode = jsonObject.getString("roleCode");
            String roleName = jsonObject.getString("roleName");
            log.warn("THE ROLE CODE:{}\nTHE ROLE NAME:{}", roleCode, roleName);
            role.setRoleCode(roleCode);
            role.setRoleName(roleName);
            if (roleService.countRoleByCodeOrName(role) != 0) {
                log.warn("countRoleByCodeOrName的if子句中role对象为:{}", role);
                return "rename";
            } else {
                role.setCreateDate(new Date());
                role.setCreatedBy(((User) session.getAttribute(Constants.SESSION_USER)).getLoginCode());
                role.setIsStart(1);
                log.warn("countRoleByCodeOrName的else子句中role对象为:{}", role);
                if (roleService.addRole(role) == 1) {
                    return "success";
                } else {
                    log.error("ADD ROLE FAILED,THE ROLE IS :{}", role);
                    return "failed";
                }
            }
        } catch (Exception e) {
            log.warn("进入异常:{}", role);
            e.printStackTrace();
            return "failed";
        }
    }

    @RequestMapping(value = "/backend/modifyRole.html", method = RequestMethod.POST)
    @ResponseBody
    public String modifyRole(@RequestParam(value = "role") String roleJson, HttpSession session) {
        if (roleJson == null || roleJson.trim().equals("")) {
            return "nodata";
        }
        Role role = new Role();
        try {
            JSONObject jsonObject = JSONObject.parseObject(roleJson);
            String roleCode = jsonObject.getString("roleCode");
            String roleName = jsonObject.getString("roleName");
            String id = jsonObject.getString("id");
            log.warn("ID:{}\nTHE ROLE CODE:{}\nTHE ROLE NAME:{}", id, roleCode, roleName);
            role.setId(Integer.valueOf(id));
            role.setRoleCode(roleCode);
            role.setRoleName(roleName);
            //排除重名
            if (roleService.countRoleByCodeOrName(role) != 0) {
                log.warn("countRoleByCodeOrName的if子句中role对象为:{}", role);
                return "rename";
            } else {
                log.warn("countRoleByCodeOrName的else子句中role对象为:{}", role);
                //该方法自动修改用户列表中的角色名称roleName(包含事务)
                int i = roleService.hl_updateRole(role);
                return "success";
            }
        } catch (Exception e) {
            log.warn("进入异常:{}", role);
            e.printStackTrace();
            return "failed";
        }
    }

    @RequestMapping(value = "/backend/delRole.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String delRole(@RequestParam(value = "role") String roleJson) {
        log.warn("delete role:{}", roleJson);
        if (roleJson == null || roleJson.equals("")) {
            return "nodata";
        }
        Role role = null;
        try {
            role = JSONObject.parseObject(roleJson, Role.class);
        } catch (Exception e) {
            log.warn("类型转换异常:roleJson={}", roleJson);
            e.printStackTrace();
            return "failed";
        }
        try {
            //先判断是否有该角色的用户
            User user = new User();
            user.setRoleId(role.getId());
            List<User> userList = userService.getUserList(user);
            if (userList.size() == 0) {
                int i = roleService.delRole(role);
                return "success";
            } else {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < userList.size(); i++) {
                    s.append(userList.get(i).getUserName() + ",");
                    if (i >= 2) {
                        log.warn("s.charAt(s.length()-1)={}",s.charAt(s.length()-1));
                        s.deleteCharAt(s.length()-1);
                        s.append(" 等");
                        break;
                    }
                }
                if(s.charAt(s.length()-1)==','){
                    s.deleteCharAt(s.length()-1);
                }
                return JSONObject.toJSONString(s.toString());
            }
        } catch (Exception e) {
            log.warn("删除用户失败:role={}", role);
            e.printStackTrace();
            return "failed";
        }
    }
}
