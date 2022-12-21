package top.himenma.rinetdconfig.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.himenma.rinetdconfig.common.annotation.LogHandler;
import top.himenma.rinetdconfig.common.api.CommonResult;

@CrossOrigin("*")
@RestController
public class LoginController {

    private static final String U = "M";
    private static final String P = "M";

    @RequestMapping("/login/{username}/{password}")
    @LogHandler(title = "登录页login")
    public CommonResult login(@PathVariable("username") String username, @PathVariable("password") String password) {
        if (U.equals(username) && P.equals(password)) {
            return CommonResult.success(null);
        }
        return CommonResult.failed("账号或密码错误，请重试！");
    }
}
