package com.liuyan.component;

import com.liuyan.handle.AbstractHandler;
import com.liuyan.handle.HandlerType;
import org.springframework.stereotype.Component;

@Component
@HandlerType("1")
public class Handle1 extends AbstractHandler {

    @Override
    public String handle() {
        System.out.println("function 1");
        return "function 1";
    }
}
