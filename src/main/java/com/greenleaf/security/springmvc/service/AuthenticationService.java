package com.greenleaf.security.springmvc.service;

import com.greenleaf.security.springmvc.model.AuthenticationRequest;
import com.greenleaf.security.springmvc.model.UserDto;

public interface AuthenticationService {
    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求，包括账号和密码信息
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
