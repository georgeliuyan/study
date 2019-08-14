/**
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 17:01
 **/

package com.liuyan.controller;

import com.liuyan.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @class: study
 * @description:
 * @author: liuyan
 * @create: 2019-08-14 17:01
 **/
@Controller
@RequestMapping("/url")
public class UrlController {

    @Autowired
    ResourceService resourceService;

    @GetMapping("/update")
    @ResponseBody
    public List<String> getAllUrl(){

        try {
            return resourceService.updateLocalUrl();
        } catch (Exception e) {
            return null;
        }
    }
}


