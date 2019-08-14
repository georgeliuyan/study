/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-13 13:24
 **/
package com.liuyan.repository;

import com.liuyan.pojo.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceDao extends CrudRepository<Resource,String> {

    Resource findByUrlName(String url);
}
