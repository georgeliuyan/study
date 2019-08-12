# study
springboot各类技术的学习

# abstracthandler
abstracthandler实现了自定义注解类，来实现对方法的标记，通过bean获取的方式，调用不用的方法。

HandleType自定义注解

···
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    String value();
}
···

# freemarker
springboot整合freemarker，实现对静态资源的访问
