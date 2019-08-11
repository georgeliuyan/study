package com.liuyan.service.impl;

import com.liuyan.handle.AbstractHandler;
import com.liuyan.handle.HandlerContext;
import com.liuyan.service.IFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionImpl implements IFunction {

    @Autowired
    private HandlerContext handlerContext;

    @Override
    public String handle(String type) {
        AbstractHandler handler = handlerContext.getInstance(type);
        return handler.handle();
    }
}
