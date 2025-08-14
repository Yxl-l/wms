package com.lemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.domain.LoginInfo;
import com.lemon.domain.PageResult;
import com.lemon.domain.User;
import com.lemon.domain.pageParam.UserPageParam;

public interface UserService extends IService<User> {
    LoginInfo login(User  user);

    PageResult getPage(UserPageParam userPageParam);
}
