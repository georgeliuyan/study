package com.liuyan.handle;

import java.util.Map;

@SuppressWarnings("unchecked")
public class HandlerContext{

    private Map<String, Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public Class getInstance(String type) {
        Class clazz = handlerMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }
        return clazz;
    }

}
