/**
 * @description: 测试启动类
 * @author: liuyan
 * @create: 2019-08-12 09:59
 **/

package com.liuyan;

/**
 * @class: study
 * @description: 测试启动类
 * @author: liuyan
 * @create: 2019-08-12 09:59
 **/
import com.liuyan.controllerTest.FreeMarkerCtrlTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FreeMarkerCtrlTest.class})
public class TestAll {
//不需要代码，只有注解就可以
}


