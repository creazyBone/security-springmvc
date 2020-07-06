package com.greenleaf.security.springmvc.controller;

import com.greenleaf.security.springmvc.model.AuthenticationRequest;
import com.greenleaf.security.springmvc.model.UserDto;
import com.greenleaf.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = {"text/plain;charset=UTF-8"})
    public String login(AuthenticationRequest authenticationRequest,HttpSession httpSession){
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        //存入session
        httpSession.setAttribute(UserDto.SESSION_USER_KEY,userDto);
        return "【"+userDto.getFullname()+"】"+"登录成功！";
    }

    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession httpSession){
        String fullName;
        Object object = httpSession.getAttribute(UserDto.SESSION_USER_KEY);
        if(object == null){
            fullName = "匿名";
        }else{
            UserDto userDto = (UserDto) object;
            fullName = userDto.getFullname();
        }
        return fullName + "访问资源r1";
    }

    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession httpSession){
        String fullName;
        Object object = httpSession.getAttribute(UserDto.SESSION_USER_KEY);
        if(object == null){
            fullName = "匿名";
        }else{
            UserDto userDto = (UserDto) object;
            fullName = userDto.getFullname();
        }
        return fullName + "访问资源r2";
    }

    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "退出成功！";
    }
}
