package com.greenleaf.security.springmvc.service;

import com.greenleaf.security.springmvc.model.AuthenticationRequest;
import com.greenleaf.security.springmvc.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static Map<String, UserDto> userMap = new HashMap<>();

    static{
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");
        userMap.put("zhangsan", new UserDto("1010", "zhangsan", "123456", "张三", "123456",authorities1));
        userMap.put("lisi", new UserDto("1011", "lisi", "111111", "李四", "111111",authorities2));
    }

    /**
     * 校验用户的身份信息
     *
     * @param authenticationRequest 用户认证请求，包括账号和密码信息
     * @return
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        //检查参数是否为空
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("用户名或密码为空");
        }
        //模拟数据库查询
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if(userDto == null){
            throw new RuntimeException("查询不到该用户");
        }
        //校验密码
        if(!authenticationRequest.getPassword().equals(userDto.getPassword())){
            throw new RuntimeException("账号或密码错误");
         }
        return userDto;
    }

    private UserDto getUserDto(String username) {
        return userMap.get(username);
    }


}
