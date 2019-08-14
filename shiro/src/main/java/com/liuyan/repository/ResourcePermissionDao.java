/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:24
 **/
package com.liuyan.repository;

import com.liuyan.pojo.ResourcePermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:24
 **/
public interface ResourcePermissionDao extends CrudRepository<ResourcePermission,String> {
    List<ResourcePermission> findAll();

    List<ResourcePermission> findByResourceId(String resourceId);
}
