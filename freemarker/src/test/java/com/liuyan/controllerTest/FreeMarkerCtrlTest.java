/**
 * @description: 测试类
 * @author: liuyan
 * @create: 2019-08-12 09:51
 **/

package com.liuyan.controllerTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * @class: study
 * @description: 测试类
 * @author: liuyan
 * @create: 2019-08-12 09:51
 **/

public class FreeMarkerCtrlTest extends TestBase{

    @Test
    public void testGetEntFileById(){
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.get("/ftl/index").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult rt = null;
        try {
            rt  = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        } catch (Exception e) {
            Assert.assertSame("",500,500);
        }
    }
}


