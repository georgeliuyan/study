/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:24
 **/
package com.liuyan.repository;

import com.liuyan.pojo.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:24
 **/
public interface PermissionDao extends CrudRepository<Permission,String> {
}
