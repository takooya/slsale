package org.slsale.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slsale.common.Constants;
import org.slsale.common.RedisAPI;
import org.slsale.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author takooya
 * @Description
 * @Date:created in 20:47 2018/5/2
 */
@Slf4j
public class SysInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisAPI redis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        String urlPath = request.getRequestURI();
        if(session==null){
            response.sendRedirect("/");
            return false;
        }else{
            User user= (User) session.getAttribute(Constants.SESSION_USER);
            String keyString ="Role" + user.getRoleId() + "UrlList";
            String urlsString= "url:"+redis.get(keyString);
            log.warn("urlsString.indexOf(urlPath)={}",urlsString.indexOf(urlPath));
            if(null!=urlsString&&!("".equals(urlsString))&&urlsString.indexOf(urlPath)>0){
                return true;
            }else{
                //401页面是无权限访问页面
                response.sendRedirect("/401.html");
                return false;
            }
        }
    }
}
