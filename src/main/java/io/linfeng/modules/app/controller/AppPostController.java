package io.linfeng.modules.app.controller;

import cn.hutool.core.util.ObjectUtil;
import io.linfeng.common.utils.AppPageUtils;
import io.linfeng.common.utils.R;
import io.linfeng.modules.admin.entity.AppUserEntity;
import io.linfeng.modules.admin.service.PostService;
import io.linfeng.modules.app.annotation.Login;
import io.linfeng.modules.app.annotation.LoginUser;
import io.linfeng.modules.app.service.PostCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linfeng
 * @date 2022/7/27 14:18
 */
@Api(tags = "用户端——帖子")
@RestController
@RequestMapping("app/post")
public class AppPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostCollectionService postCollectionService;


    @GetMapping("/lastPost")
    @ApiOperation("最新动态列表")
    public R lastPost(@RequestParam Integer page){

        AppPageUtils pages =postService.lastPost(page);
        return R.ok().put("result", pages);
    }


    @Login
    @GetMapping("/followUserPost")
    @ApiOperation("获取关注用户帖子")
    public R followUserPost(@RequestParam Integer page, @LoginUser AppUserEntity user){

        AppPageUtils pages =postService.followUserPost(page,user);
        if(ObjectUtil.isNull(page)){
            return R.error("您没有关注的用户");
        }
        return R.ok().put("result", pages);
    }

}