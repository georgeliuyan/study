package com.liuyan.component;

import com.liuyan.handle.AbstractHandler;
import com.liuyan.handle.HandlerType;
import org.springframework.stereotype.Component;

@Component
@HandlerType("2")
public class Handle2 extends AbstractHandler {

    @Override
    public String handle() {
        System.out.println("function 2");
        return "function 2";
    }
}
