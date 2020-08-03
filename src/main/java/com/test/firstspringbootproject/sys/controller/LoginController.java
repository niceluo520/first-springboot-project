package com.test.firstspringbootproject.sys.controller;

import com.test.firstspringbootproject.api.common.rabbit.ProviderLog;
import com.test.firstspringbootproject.sys.domain.UserLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *  @ResponseBody : 返回json的数据格式
 *  @RestController = @Controller + @ResponseBody
 *
 */
@Api(tags = "登录接口")
@Controller
public class LoginController {

    @Autowired
    private ProviderLog producerLog;

    @ApiOperation("跳转登录页面")
    @GetMapping("/toLogin")
    String welcome() {
        return "login";
    }

    @GetMapping("/play")
    String login(Model model) {
        model.addAttribute("name","哈哈哈");
        return "play";
    }

    @GetMapping("/add")
    String add() {
        return "/user/add";
    }

    @GetMapping("/update")
    String update() {
        return "/user/update";
    }

    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/doLogin")
    public String doLogin(String username,String password,Model model){
        /**
         * 使用shiro编写认证操作
         *
         */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);

            //向生产这发送用户日志信息
            UserLog log = new UserLog();
            log.setLog_name("用户登录");
            log.setLog_type("login");
            log.setUser_name(token.getUsername());
            producerLog.send(log);
            //登录成功后跳转页面
            return "/play";
        }catch (UnknownAccountException e){
            //  用户名不存在   直接跳转页面（带参数跳转）
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            // 密码不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @GetMapping("/404")
    public String test(){
        return "404";
    }


}
