/**
 * @description: 数据访问层
 * @author: liuyan
 * @create: 2019-08-13 12:48
 **/
package com.liuyan.repository;

import com.liuyan.pojo.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @class: study
 * @description: 数据访问层
 * @author: liuyan
 * @create: 2019-08-13 12:48
 **/
public interface UserRoleDao extends CrudRepository<UserRole,String> {
    List<UserRole> findByUserId(String userId);
}
