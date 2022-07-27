package io.linfeng.modules.app.controller;

import io.linfeng.common.utils.R;
import io.linfeng.common.utils.RedisUtils;
import io.linfeng.modules.admin.service.AppUserService;
import io.linfeng.modules.app.form.SmsLoginForm;
import io.linfeng.modules.app.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * APP登录接口
 * @author linfeng
 * @date 2022/6/9 22:40
 */
@RestController
@RequestMapping("/app/user")
@Api(tags = "APP登录接口")
public class AppLoginController {


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RedisUtils redisUtils;







    /**
     * 手机验证码登录
     */
    @PostMapping("/smsLogin")
    @ApiOperation("手机验证码登录")
    public R smsLogin(@RequestBody SmsLoginForm form, HttpServletRequest request){

        //用户登录
        Integer userId = appUserService.smsLogin(form,request);

        //生成token
        String token = jwtUtils.generateToken(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return R.ok(map);
    }

}
