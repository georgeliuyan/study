package com.liuyan.config;

import com.liuyan.realm.UserRealm;
import com.liuyan.service.ResourcePermissionService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    ResourcePermissionService resourcePermissionService;

    /*
     *  功能描述：
     *      该函数是用来生成一个匹配器，匹配器是用来检查“户登录使用的令牌”和“数据库中保存的用户信息”是否匹配
     *      这里对HashedCredentialsMatcher进行算法设计和执行次数，作为后续对用户信息进行加密校验的方法。
     *      HashedCredentialsMatcher类是CredentialsMatcher接口的实现类。
     *
     *  入参描述：null
     *
     *  出参描述：返回一个匹配器
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /*
     *  功能描述：new一个自定义的UserRealm对象，并添加上面配置好的匹配器
     *
     *  参数描述：HashedCredentialsMatcher 匹配器
     *
     *  出参描述：返回自定义的UserRealm
     */
    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    /*
     *  功能描述：
     *      为shiro生成securityManager安全管理器，并注册成为bean。
     *      securityManager主要是身份认证的管理，缓存管理，cookie管理。
     *      这里我们给securityManager手动配置了userRealm对象，进行身份认证管理。
     *
     *  参数描述：HashedCredentialsMatcher 匹配器
     *
     *  出参描述：返回自定义的UserRealm
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(HashedCredentialsMatcher hashedCredentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }

    /*
     *  功能描述：
     *      利用注册好的安全管理器，构建一个过滤器，过滤器配置了用户的接口访问规则。
     *
     *  参数描述：注入的安全管理器bean对象
     *
     *  出参描述：ShiroFilterFactoryBean过滤器对象
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
        // 设置登录成功后的欢迎页面
        bean.setSuccessUrl("/main");
        // 设置未登录时的跳转页面
        bean.setLoginUrl("/toLogin");
        // 设置未认证的页面
        bean.setUnauthorizedUrl("/error/unAuth");

        /**
         * Shiro内置过滤器，可以实现拦截器相关的拦截器
         *    常用的过滤器：
         *      anon：无需认证（登录）可以访问
         *      authc：必须认证才可以访问，
         *      user：如果使用rememberMe的功能可以直接访问
         *      perms：该资源必须得到资源权限才可以访问
         *      role：该资源必须得到角色权限才可以访问
         *
         *      配置接口权限时，filterMap.put("/vip/index","roles[vip]"); 表示"/vip/index"接口只有角色vip可以访问
         *      如果配置 filterMap.put("/vip/index","perms[user:vip]"); 表示又有权限"user:vip"的用户才能访问
         *
         *      perms的写法并不影响功能的实现，编写规则如下
         *
         *    单个权限 query
         *    单个资源多个权限 user:query user:add 多值 user:query,add
         *    单个资源所有权限 user:query,add,update,delete user:*
         *    所有资源某个权限 *:view
         *    实例级别的权限控制
         *    单个实例的单个权限 printer:query:lp7200 printer:print:epsoncolor
         *    所有实例的单个权限 printer:print:*
         *    所有实例的所有权限 printer:*:*
         *    单个实例的所有权限 printer:*:lp7200
         *    单个实例的多个权限 printer:query,print:lp7200
         *    字符串通配符权限
         *    规则：“资源标识符：操作：对象实例ID”  即对哪个资源的哪个实例可以进行什么操作。其默认支持通配符权限字符串，“:”表示资源/操作/实例的分割；“,”表示操作的分割；“*”表示任意资源/操作/实例。
         *
         *    资源标识符:请求url的标识
         *    操作：操作描述符
         *    对象实例ID：这个还不是很清楚代表的什么
         *
         *      过滤链定义，从上向下顺序执行，一般将/**放在最为下边，这是一个坑呢，一不小心代码就不好使了。
         **/
        Map<String, String> filterMap = resourcePermissionService.getResourcePermissionMap();

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }
}