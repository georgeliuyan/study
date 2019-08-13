/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:25
 **/
package com.liuyan.repository;

import com.liuyan.pojo.RolePermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:25
 **/
public interface RolePermissionDao extends CrudRepository<RolePermission,String> {

    List<RolePermission> findByRoleId(String roleId);
}
