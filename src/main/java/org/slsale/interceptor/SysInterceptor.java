package org.slsale.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.common.RedisAPI;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.User;
import org.slsale.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 20:47 2018/5/2
 */
@Slf4j
public class SysInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private FunctionService functionService;

    @Autowired
    private RedisAPI redis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String urlPath = request.getRequestURI();
        if (session == null) {
            response.sendRedirect("/");
            return false;
        } else {
            User user = (User) session.getAttribute(Constants.SESSION_USER);
            String keyString = "Role" + user.getRoleId() + "UrlList";
            String urlsString = "";
            //start没有再redis中找到角色权限对应的Url值
            if (!redis.exicts("Role" + user.getRoleId() + "UrlList")) {
                try {
                    Authority authority = new Authority();
                    authority.setRoleId(user.getRoleId());
                    List<Function> functionList = functionService.getFunctionUrlByRoreId(authority);
                    if (functionList != null && functionList.size() >= 0) {
                        StringBuffer sb = new StringBuffer();
                        for (Function f : functionList) {
                            sb.append(f.getFuncUrl());
                        }
                        urlsString = "url:" + sb.toString();
                    }
                } catch (Exception e) {
                    log.warn("redis和数据库中均没有查到角色权限对应的Url值信息");
                    e.printStackTrace();
                }
            } else {
                urlsString = "url:" + redis.get(keyString);
            }
            //end没有再redis中找到角色权限对应的Url值
            log.warn("urlsString.indexOf(urlPath)={}", urlsString.indexOf(urlPath));
            if (null != urlsString && !("".equals(urlsString)) && urlsString.indexOf(urlPath) > 0) {
                return true;
            } else {
                //401页面是无权限访问页面
                response.sendRedirect("/401.html");
                return false;
            }
        }
    }
}
