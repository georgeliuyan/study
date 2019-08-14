/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 16:53
 **/

package com.liuyan.service.impl;

import com.liuyan.pojo.ResourcePermission;
import com.liuyan.repository.ResourcePermissionDao;
import com.liuyan.service.ResourcePermissionService;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourcePermissionServiceImpl implements ResourcePermissionService {

    @Autowired
    ResourcePermissionDao resourcePermissionDao;

    @Override
    public Map<String, String> getResourcePermissionMap() {
        Map<String, String> filterMap = new LinkedHashMap<>();
        List<ResourcePermission> list =  resourcePermissionDao.findAll();
        for (ResourcePermission rp : list){
            if (rp.getResourceIdent() == null || rp.getResourceIdent().isEmpty()){
                filterMap.put(rp.getResourceUrl(),rp.getPermissionIdent());
            }
            else
                filterMap.put(rp.getResourceUrl(),rp.getResourceIdent()+":"+rp.getPermissionIdent());
        }

        return filterMap;
    }
}


