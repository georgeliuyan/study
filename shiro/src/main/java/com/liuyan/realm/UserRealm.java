package com.liuyan.realm;


import com.liuyan.pojo.*;
import com.liuyan.repository.PermissionDao;
import com.liuyan.repository.RoleDao;
import com.liuyan.repository.RoleResourcePermissionDao;
import com.liuyan.repository.UserRoleDao;
import com.liuyan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/*
 * Shiro的认证过程最终会交由Realm执行,这时会调用Realm的getAuthenticationInfo(token)方法。
 * 我们要做的就是自定义一个Realm类，继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo()，重写获取用户信息的方法。
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    RoleDao roleDao;

    @Autowired
    RoleResourcePermissionDao roleResourcePermissionDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    PermissionDao permissionDao;

    @Autowired
    private UserService userService;

    /**
     * 构造一个权限管理逻辑，最后由shiro注册到自己的securityManager中
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");

        Subject subject = SecurityUtils.getSubject();
        //获取用户的输入的账号
        Users user = (Users)subject.getPrincipal();
        if(user != null){
            //待返回的AuthorizationInfo
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            // 角色与权限URL字符串集合
            Collection<String> rolesCollection = new HashSet<>();
            Collection<String> premissionCollection = new HashSet<>();

            List<UserRole> userRoles = userRoleDao.findByUserId(user.getId());
            for(UserRole userRole : userRoles){
                Role role = roleDao.findById(userRole.getRoleId()).orElse(null);
                rolesCollection.add(role.getName());
                List<RoleResourcePermission> rrps = roleResourcePermissionDao.findByRoleId(role.getId());
                for (RoleResourcePermission rrp : rrps){
                    premissionCollection.add(rrp.getResourceIdent()+":"+rrp.getPermissionIdent());
                }
                info.addStringPermissions(premissionCollection);
            }
            info.addRoles(rolesCollection);
            return info;
        }
        return null;
    }

    /**
     * 认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行认证");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        Users user = userService.findByName(token.getUsername());

        if(user == null){
            throw new UnknownAccountException();
        }

        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getName());

        return new SimpleAuthenticationInfo(user, user.getPassword(),
                credentialsSalt, getName());
    }

    public static void main(String[] args){
        String hashAlgorithName = "MD5";
        String password = "123456";
        //加密次数
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("vip");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
