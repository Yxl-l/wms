package com.lemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lemon.domain.LoginInfo;
import com.lemon.domain.PageResult;
import com.lemon.domain.User;
import com.lemon.domain.pageParam.UserPageParam;
import com.lemon.mapper.UserMapper;
import com.lemon.service.UserService;
import com.lemon.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult getPage(UserPageParam userPageParam) {
        PageHelper.startPage(userPageParam.getPage(),userPageParam.getPageSize());
        List<User> page = userMapper.getPage(userPageParam);
        Page<User> pageInfo = (Page<User>) page;
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getResult());
    }

    @Override
    public LoginInfo login(User user) {
        User login = userMapper.login(user.getUsername(), user.getPassword());
        if (login != null){
            Map<String, Object> map = new HashMap<>();
            map.put("id",login.getId());
            map.put("username",login.getUsername());
            return new LoginInfo(login.getId(),login.getUsername(),login.getName(), JwtUtils.generateJwt(map));
        }
        return null;
    }
}
