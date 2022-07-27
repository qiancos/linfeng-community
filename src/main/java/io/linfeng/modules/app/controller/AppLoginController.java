package io.linfeng.modules.app.controller;

import io.linfeng.common.response.AppUserResponse;
import io.linfeng.common.utils.R;
import io.linfeng.common.utils.RedisUtils;
import io.linfeng.common.validator.ValidatorUtils;
import io.linfeng.modules.admin.entity.AppUserEntity;
import io.linfeng.modules.admin.service.AppUserService;
import io.linfeng.modules.app.annotation.Login;
import io.linfeng.modules.app.annotation.LoginUser;
import io.linfeng.modules.app.form.AddFollowForm;
import io.linfeng.modules.app.form.AppUserUpdateForm;
import io.linfeng.modules.app.form.SendCodeForm;
import io.linfeng.modules.app.form.SmsLoginForm;
import io.linfeng.modules.app.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    @PostMapping("/sendSmsCode")
    @ApiOperation("发送验证码")
    public R sendSmsCode(@RequestBody SendCodeForm param){
        String code=appUserService.sendSmsCode(param);
        return R.ok("测试阶段验证码:"+code);
    }




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


    @Login
    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser AppUserEntity user){

        AppUserResponse response=appUserService.getUserInfo(user);
        return R.ok().put("result", response);
    }


    @Login
    @PostMapping("/userInfoEdit")
    @ApiOperation("用户修改个人信息")
    public R userInfoEdit(@LoginUser AppUserEntity user, @RequestBody AppUserUpdateForm appUserUpdateForm){
        appUserService.updateAppUserInfo(appUserUpdateForm,user);
        return R.ok("修改成功");
    }



    @Login
    @PostMapping("/addFollow")
    @ApiOperation("关注用户")
    public R addFollow(@LoginUser AppUserEntity user, @RequestBody AddFollowForm request){
        appUserService.addFollow(request,user);
        return R.ok("关注用户成功");
    }


    @Login
    @PostMapping("/cancelFollow")
    @ApiOperation("取消关注用户")
    public R cancelFollow(@LoginUser AppUserEntity user, @RequestBody AddFollowForm request){
        appUserService.cancelFollow(request,user);
        return R.ok("取消关注用户成功");
    }

}
