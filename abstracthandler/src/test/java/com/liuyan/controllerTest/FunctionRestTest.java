/**
 * @description: 测试类
 * @author: liuyan
 * @create: 2019-08-12 12:20
 **/

package com.liuyan.controllerTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class FunctionRestTest extends TestBase {

    @Test
    public void getTestAll(){
        getTest("1");
        getTest("2");
    }

    public void getTest(String type){
        RequestBuilder request = MockMvcRequestBuilders.get("/api/get").accept(MediaType.APPLICATION_JSON_UTF8).param("type",type)
                .contentType(MediaType.APPLICATION_JSON);

        try {
            String jsonrest = this.mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
            Assert.assertEquals("function "+type,jsonrest);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}


