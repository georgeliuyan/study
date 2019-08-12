package com.liuyan.service.impl;

import com.liuyan.handle.AbstractHandler;
import com.liuyan.handle.HandlerContext;
import com.liuyan.service.IFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionImpl implements IFunction {

    @Autowired
    private HandlerContext handlerContext;  //处理器上下文

    @Override
    public String handle(String type) {
        AbstractHandler handler = handlerContext.getInstance(type); //获取到一个抽象的处理器，我们的处理器方法是继承AbstractHandler的
        return handler.handle();
    }
}
