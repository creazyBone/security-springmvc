package com.greenleaf.security.springmvc.interceptor;


import com.greenleaf.security.springmvc.model.UserDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    /**
     * 在这个方法中校验用户的请求是否有权限访问资源
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    //在目标方法调用之前被调用。若返回值为true,则继续调用后续的拦截器和目标方法；若返回值为false,则不会再调用后续的拦截器和目标方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //取出用户身份信息
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        //没有认证，提示登录
        if(object == null){
            writeContent(response,"请登录");
        }
        UserDto userDto = (UserDto) object;
        Set<String> authorities = userDto.getAuthorities();
        //获取请求的URI
        String requestURI = request.getRequestURI();
        if(authorities.contains("p1") && requestURI.contains("r/r1")){
            return true;
        }
        if(authorities.contains("p2") && requestURI.contains("r/r2")){
            return true;
        }
        writeContent(response,"对不起，您没有访问权限！");
        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(msg);
        pw.close();
        response.resetBuffer();
    }

    //目标方法调用之后，渲染视图之前被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    //渲染视图之后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}



