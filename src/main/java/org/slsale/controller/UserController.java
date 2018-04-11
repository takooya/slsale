package org.slsale.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.pojo.Menu;
import org.slsale.pojo.User;
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

    @RequestMapping(value = "/backend/modify.html", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(HttpSession session, @RequestParam String info) {
        if(null==info||"".equals(info)){
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
    public ModelAndView userlist(Model model,HttpSession session) {
        Map<String, Object> baseModel = (Map<String, Object>) session.getAttribute(Constants.SESSION_BASE_MODEL);
        if(model==null){
            return new ModelAndView("redirect:/");
        }else{
            //获取roleList
            model.addAllAttributes(baseModel);
            return new ModelAndView("backend/userlist");
        }
    }
}