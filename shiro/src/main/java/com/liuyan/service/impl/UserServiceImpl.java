package com.liuyan.service.impl;

import com.liuyan.pojo.Users;
import com.liuyan.repository.UsersDao;
import com.liuyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public Users findByName(String name) {
        // 查询用户是否存在
        Users user = usersDao.findByName(name);
        if (user != null) {
            // 查询用户信息、角色、权限
            /*
             * JDK 1.8新增加的Optional类型
             * jpa中将findById的返回值修改为Optional<T>，增加了对返回值为空的判断
             * 增加的orElse，
             */
            user = usersDao.findById(user.getId()).orElse(null);
        }
        return user;
    }
}