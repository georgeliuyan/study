/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 17:06
 **/

package com.liuyan.service.impl;

import com.liuyan.pojo.Resource;
import com.liuyan.pojo.ResourcePermission;
import com.liuyan.repository.ResourceDao;
import com.liuyan.repository.ResourcePermissionDao;
import com.liuyan.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 17:06
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    ResourceDao resourceDao;

    @Autowired
    ResourcePermissionDao resourcePermissionDao;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<String> updateLocalUrl() {
        String localIp = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            localIp = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo,HandlerMethod> map = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()){
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String url : patterns){
                if ( url.equals("/url/update") || url.equals("/error"))
                    continue;
                if (resourceDao.findByUrlName(url) == null)
                {
                    Resource resource = new Resource();
                    resource.setUrlName(url);
                    resource.setUrlIdent(localIp + ":" +url);
                    resourceDao.save(resource);
                    ResourcePermission rp = new ResourcePermission();
                    rp.setPermissionId("");
                    rp.setPermissionIdent("authc");
                    rp.setResourceId(resource.getId());
                    rp.setResourceIdent(resource.getUrlIdent());
                    rp.setResourceUrl(resource.getUrlName());
                    resourcePermissionDao.save(rp);
                }
                urlList.add(url);
            }
        }
        return urlList;
    }
}


