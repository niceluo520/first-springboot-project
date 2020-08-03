package com.test.firstspringbootproject.api.shiro;

import com.test.firstspringbootproject.sys.domain.User;
import com.test.firstspringbootproject.sys.server.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

//登录及权限认证

/**
 * 自定义类
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    //用户权限和对应权限添加   授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");

        User user = (User) principals.getPrimaryPrincipal();
        User u = sysUserService.findById(user.getId());
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission(u.getHelp());
        return info;
    }

    //用户身份验证         身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        String name = (String) token.getPrincipal();
       /* UsernamePasswordToken token1 = (UsernamePasswordToken)token;
        token1.getUsername();*/
        User user = sysUserService.confirmUser(name);
        if(user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
