package com.liuyan.service.impl;

import com.liuyan.handle.AbstractHandler;
import com.liuyan.handle.HandlerContext;
import com.liuyan.service.IFunction;
import com.liuyan.utils.BeanTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionImpl implements IFunction {

    @Autowired
    private BeanTool beanTool;  //处理器上下文

    @Override
    public String handle(String type) {
        /*
         * HandlerContext类本身是非spring管理的类，但是在HandleProcessor中手动的把它交给了spring管理
         * 后续用@Autowired直接注入也是可以的，但是IDE不知道这个bean是否存在，会提示没有bean
         *
         * 建议改成下面手动获取的方式，有两个好处：
         *  1、IDE不会再提示找不到HandlerContext的bean
         *  2、beanTool上下文获取工具在这里也可以注入，它的变量applicationContext就可以设置为非static，避免静态检查报错
         */
        HandlerContext handlerContext = (HandlerContext)beanTool.getBean(HandlerContext.class);
        AbstractHandler handler = (AbstractHandler) beanTool.getBean(handlerContext.getInstance(type)); //获取到一个抽象的处理器，我们的处理器方法是继承AbstractHandler的
        return handler.handle();
    }
}
