package com.test.firstspringbootproject.api.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.test.firstspringbootproject.api.shiro.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//shiro配置类  为了注册到springboot中去
@Configuration
public class ShiroConfig {

    //自定义Realm类    将自己的验证方式加入容器
    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }

    //创建DefaultWebSecurityManager   权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //管理Realm
        manager.setRealm(myRealm());
        return manager;
    }

    //创建ShiroFilterFactoryBean       Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager());

        //添加shiro内置过滤器
        /**
         *   shiro 内置过滤器可以实现相关拦截
         *       anon : 无需认证登录就可以访问
         *       authc : 必须认证才可以访问
         *       user  : 如果使用rememberMe的功能可以直接访问
         *       perms : 必须得到资源权限才可以访问
         *       role  : 必须得到角色权限才可以访问
         */

        //修改跳转页面
        bean.setLoginUrl("/toLogin");
        //未授权跳转页面
        bean.setUnauthorizedUrl("/404");

        Map<String, String> map = new LinkedHashMap<>();
        //map.put("/add","authc");
        //map.put("/update","authc");
        //授权过滤器    当未授权，会自动跳转未授权页面
        map.put("/add","perms[user:add]");
        map.put("/update","perms[user:update]");
        map.put("/swagger-ui.html","anon");
        map.put("/v2/**","anon");
        map.put("/swagger-resources/**","anon");
        map.put("/doLogin","anon");
        //map.put("/play","anon");
        map.put("/**","authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

     /**
      * 页面上使用shiro标签
      * @return
      */
     @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
