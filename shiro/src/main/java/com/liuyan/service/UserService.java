package com.liuyan.service;


import com.liuyan.pojo.Users;

public interface UserService {

    Users findByName(String name);
}
