# study
springboot各类技术的学习

# 一、自定义处理器 abstracthandler
abstracthandler实现了自定义注解类，来实现对方法的标记，通过bean获取的方式，调用不用的方法。

1、创建HandleType自定义注解
```
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    String value();
}
```
2、定义一个抽象处理器
```
public abstract class AbstractHandler {
    abstract public String handle();
}
```

3、将处理器注册到spring容器中
    1）扫描指定包中标有@HandlerType的类；
    2）将注解中的类型值作为key，对应的类作为value，保存在Map中；
    3）以上面的map作为构造函数参数，初始化HandlerContext，将其注册到spring容器中。

    代码参考HandlerProcessor.java
    HandlerProcessor需要实现BeanFactoryPostProcessor，在spring处理bean前，将自定义的bean注册到容器中。

4、BeanTool：获取bean工具类
5、ClassScaner：注解类扫描工具类

# 二、整合freemarker
springboot整合freemarker，实现对静态资源的访问

# 三、整合shiro实现用户权限控制

