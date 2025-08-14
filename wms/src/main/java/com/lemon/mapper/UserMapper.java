package com.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.domain.User;
import com.lemon.domain.pageParam.UserPageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username=#{username} and password=#{password}")
    User login(String username, String password);

    List<User> getPage(UserPageParam userPageParam);
}
