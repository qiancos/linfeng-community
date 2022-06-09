package io.linfeng.modules.admin.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.linfeng.common.exception.LinfengException;
import io.linfeng.common.response.HomeRateResponse;
import io.linfeng.common.utils.*;
import io.linfeng.modules.admin.entity.PostEntity;
import io.linfeng.modules.admin.service.*;
import io.linfeng.modules.app.form.SmsLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.linfeng.modules.admin.dao.AppUserDao;
import io.linfeng.modules.admin.entity.AppUserEntity;

import javax.servlet.http.HttpServletRequest;


@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserDao, AppUserEntity> implements AppUserService {


    @Autowired
    private PostService postService;

    @Autowired
    private AppUserDao userDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AppUserEntity> queryWrapper=new QueryWrapper<>();
        //模糊查询
        String key = (String)params.get("key");
        if(!WechatUtil.isEmpty(key)){
            params.put("page","1");//如果是查询分页重置为第一页
            queryWrapper.like("username", key).or().like("mobile",key);
        }
        queryWrapper.lambda().orderByDesc(AppUserEntity::getUid);
        IPage<AppUserEntity> page = this.page(
                new Query<AppUserEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }


    @Override
    public void ban(Integer id) {
        Integer status = this.lambdaQuery().eq(AppUserEntity::getUid, id).one().getStatus();
        if(status.equals(Constant.USER_BANNER)){
            throw new LinfengException("该用户已被禁用");
        }
        this.lambdaUpdate()
                .set(AppUserEntity::getStatus,1)
                .eq(AppUserEntity::getUid,id)
                .update();
    }

    @Override
    public void openBan(Integer id) {
        Integer status = this.lambdaQuery().eq(AppUserEntity::getUid, id).one().getStatus();
        if(status.equals(Constant.USER_NORMAL)){
            throw new LinfengException("该用户已解除禁用");
        }
        this.lambdaUpdate()
                .set(AppUserEntity::getStatus,0)
                .eq(AppUserEntity::getUid,id)
                .update();
    }

    @Override
    public HomeRateResponse indexDate() {
        String today = cn.hutool.core.date.DateUtil.date().toString("yyyy-MM-dd");
        String yesterday = cn.hutool.core.date.DateUtil.yesterday().toString("yyyy-MM-dd");
//        Integer count = postService.lambdaQuery().eq(PostEntity::getStatus, Constant.POST_REVIEWED).count();
        Integer postCount = postService.lambdaQuery().select(PostEntity::getId).count();
        HomeRateResponse response = new HomeRateResponse();
        response.setTotalPostOfReview(0);
        response.setTotalPost(postCount);
        response.setNewUserNum(this.getRegisterNumByDate(today));
        response.setYesterdayNewUserNum(this.getRegisterNumByDate(yesterday));
        response.setTotalUser(this.getTotalNum());
        return response;
    }

        @Override
        public Integer smsLogin(SmsLoginForm form, HttpServletRequest request) {
            AppUserEntity appUserEntity = this.lambdaQuery().eq(AppUserEntity::getMobile, form.getMobile()).one();
            String codeKey = "code_" + form.getMobile();
            String s = redisUtils.get(codeKey);
            if(!s.equals(form.getCode())){
                throw new LinfengException("验证码错误！");
            }
            if(appUserEntity!=null){
                if(appUserEntity.getStatus()==1){
                    throw new LinfengException("该账号已被禁用");
                }
                return appUserEntity.getUid();
            }else {
                List<String> list=new ArrayList<>();
                list.add("萌新");
                AppUserEntity appUser = new AppUserEntity();
                appUser.setMobile(form.getMobile());
                appUser.setAvatar(Constant.DEAULT_HEAD);
                appUser.setGender(0);
                appUser.setUsername("LF_"+RandomUtil.randomNumbers(6));
                String tag = JSON.toJSONString(list);
                appUser.setTagStr(tag);
                appUser.setCreateTime(DateUtil.nowDateTime());
                appUser.setUpdateTime(DateUtil.nowDateTime());
                baseMapper.insert(appUser);
                AppUserEntity appUsers = this.lambdaQuery().eq(AppUserEntity::getMobile, form.getMobile()).one();


                return appUsers.getUid();
            }

    }

    private Integer getTotalNum() {
        return this.lambdaQuery().select(AppUserEntity::getUid).count();
    }

    private Integer getRegisterNumByDate(String date) {
        QueryWrapper<AppUserEntity> wrapper = Wrappers.query();
        wrapper.select("uid");
        wrapper.apply("date_format(create_time, '%Y-%m-%d') = {0}", date);
        return userDao.selectCount(wrapper);
    }

}