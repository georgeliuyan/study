/**
 * @description: 测试基类
 * @author: liuyan
 * @create: 2019-08-12 09:53
 **/

package com.liuyan.controllerTest;

/**
 * @class: study
 * @description: 测试基类
 * @author: liuyan
 * @create: 2019-08-12 09:53
 **/
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liuyan.Application;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest(classes = Application.class)
public class TestBase{

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;


    @Before
    public void init() {
        //集成Web环境方式
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //独立测试方式
//        mockMvc = MockMvcBuilders.standaloneSetup(new FreeMarkerCtrl()).build();
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}


