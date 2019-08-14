/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:25
 **/
package com.liuyan.repository;

import com.liuyan.pojo.RoleResourcePermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @class: study
 * @description:s
 * @author: liuyan
 * @create: 2019-08-13 13:25
 **/
public interface RoleResourcePermissionDao extends CrudRepository<RoleResourcePermission,String> {

    List<RoleResourcePermission> findByRoleId(String roleId);
}
