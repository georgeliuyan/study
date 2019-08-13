/**
 * @description: 数据访问层
 * @author: liuyan
 * @create: 2019-08-13 12:48
 **/
package com.liuyan.repository;

import com.liuyan.pojo.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * @class: study
 * @description: 数据访问层
 * @author: liuyan
 * @create: 2019-08-13 12:48
 **/
public interface UsersDao extends CrudRepository<Users,String> {

    Users findByName(String name);
}
