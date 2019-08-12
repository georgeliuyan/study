package com.liuyan.handle;

import com.google.common.collect.Maps;
import com.liuyan.utils.ClassScaner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@SuppressWarnings("unchecked")
public class HandlerProcessor implements BeanFactoryPostProcessor {

    private static final String HANDLER_PACKAGE = "com.liuyan.component"; //待扫描的component包

    /**
     * 扫描@HandlerType，初始化HandlerContext，将其注册到spring容器
     *
     * @param beanFactory bean工厂
     * @see HandlerType
     * @see HandlerContext
     */

    //重写postProcessBeanFactory方法
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //构造一个map对象包含
        Map<String, Class> handlerMap = Maps.newHashMapWithExpectedSize(2);

        //扫描包下的注解类，将注解的value和注解的类按照键值对的形式保存到Map中
        //ClassScaner类的实现参见ClassScaner.java
        ClassScaner.scan(HANDLER_PACKAGE, HandlerType.class).forEach(clazz -> {
            String type = clazz.getAnnotation(HandlerType.class).value();
            handlerMap.put(type, clazz);
        });
        //初始化HandlerContext处理器上下文，将其注册到spring容器中
        HandlerContext context = new HandlerContext(handlerMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }

}
