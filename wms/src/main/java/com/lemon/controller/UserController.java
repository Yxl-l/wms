package com.lemon.controller;

import com.lemon.domain.LoginInfo;
import com.lemon.domain.Result;
import com.lemon.domain.User;
import com.lemon.domain.pageParam.UserPageParam;
import com.lemon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 * 登录
 * @author YXL
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return userService.removeById(id) ? Result.success() : Result.error("删除失败");
    }


    @PutMapping
    public Result update(@RequestBody User user){
        return userService.updateById(user) ? Result.success() : Result.error("更新失败");
    }

    @PostMapping
    public Result add(@RequestBody User user){
        return userService.save(user) ? Result.success() : Result.error("添加失败");
    }

    @GetMapping
    public Result getPage(UserPageParam userPageParam){
        return Result.success(userService.getPage(userPageParam));
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        LoginInfo login = userService.login(user);
        if (login != null){
            return Result.success(login);
        }
        return Result.error("用户名或密码错误");
    }

}
