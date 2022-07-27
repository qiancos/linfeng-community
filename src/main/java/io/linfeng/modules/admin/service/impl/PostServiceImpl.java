package io.linfeng.modules.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.linfeng.common.exception.LinfengException;
import io.linfeng.common.response.PostDetailResponse;
import io.linfeng.common.response.PostListResponse;
import io.linfeng.common.response.TopicDetailResponse;
import io.linfeng.common.utils.*;
import io.linfeng.modules.admin.entity.*;
import io.linfeng.modules.admin.service.*;
import io.linfeng.modules.app.entity.PostCollectionEntity;
import io.linfeng.modules.app.entity.TopicAdminEntity;
import io.linfeng.modules.app.entity.UserTopicEntity;
import io.linfeng.modules.app.form.*;
import io.linfeng.modules.app.service.*;
import io.linfeng.modules.app.utils.LocalUser;
import javafx.geometry.Pos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.linfeng.modules.admin.dao.PostDao;
import org.springframework.transaction.annotation.Transactional;


@Service("postService")
public class PostServiceImpl extends ServiceImpl<PostDao, PostEntity> implements PostService {


    @Autowired
    private AppUserService appUserService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private PostCollectionService postCollectionService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private LocalUser localUser;
    @Autowired
    private FollowService followService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PostEntity> queryWrapper=new QueryWrapper<>();
        //条件查询
        String key = (String)params.get("key");
        String status = (String)params.get("status");
        if(!WechatUtil.isEmpty(key)){
            params.put("page","1");//如果是查询分页重置为第一页
            queryWrapper.like("content", key).or().like("title",key);
        }
        if(!WechatUtil.isEmpty(status)){
            params.put("page","1");//如果是查询分页重置为第一页
            queryWrapper.eq("status", Integer.parseInt(status));
        }
        queryWrapper.lambda().orderByDesc(PostEntity::getId);
        IPage<PostEntity> page = this.page(
                new Query<PostEntity>().getPage(params),
                queryWrapper
        );
        List<PostEntity> data = page.getRecords();

        List<PostListResponse> responseList=new ArrayList<>();
        data.forEach(l->{
            PostListResponse response=new PostListResponse();
            BeanUtils.copyProperties(l,response);
            if(ObjectUtil.isNotNull(response.getDiscussId())&&response.getDiscussId()>0){
                DiscussEntity discussEntity = discussService.getById(response.getDiscussId());
                if(ObjectUtil.isNotNull(discussEntity)){
                    response.setDiscussTitle(discussEntity.getTitle());
                }
            }
            response.setTopicName(topicService.getById(response.getTopicId()).getTopicName());
            response.setCollectionCount(postCollectionService.collectCount(response.getId()));
            response.setCommentCount(commentService.getCountByTopicId(response.getId()));
            response.setUserInfo(appUserService.getById(response.getUid()));
            response.setMedia(JsonUtils.JsonToList(l.getMedia()));
            responseList.add(response);
        });
        PageUtils pageUtils=new PageUtils(page);
        pageUtils.setList(responseList);
        return pageUtils;
    }




    @Override
    @Transactional
    public void deleteByAdmin(List<Integer> ids) {
        //通知用户消息违规被删除了
        ids.forEach(postId -> {
            PostEntity post = this.getById(postId);
            String content = StrUtil.format(Constant.ADMIN_POST_DOWN, post.getTitle());
            messageService.sendMessage(0, post.getUid(), postId, Constant.PUSHARTICLE, content, Constant.TITLE_VIOLATION);
        });
        boolean remove = this.removeByIds(ids);
        if (!remove) {
            throw new LinfengException("批量删除失败");
        }
    }

    @Override
    public Integer getPostNumByUid(Integer uid) {

        return this.lambdaQuery().eq(PostEntity::getUid,uid)
                .count();
    }

    @Override
    public AppPageUtils lastPost(Integer currPage) {
        Page<PostEntity> page=new Page<>(currPage,10);
        QueryWrapper<PostEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("post_top","id");
        AppUserEntity user = localUser.getUser();
        if(user==null){
            return this.mapPostList(page,queryWrapper,0);
        }
        return this.mapPostList(page,queryWrapper,user.getUid());
    }

    @Override
    public AppPageUtils followUserPost(Integer page, AppUserEntity user) {
        List<Integer> list=followService.getFollowUid(user);
        if(list.isEmpty()){
            return null;
        }
        QueryWrapper<PostEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().in(PostEntity::getUid,list);
        queryWrapper.orderByDesc("post_top","id");
        Page<PostEntity> pages=new Page<>(page,10);
        return this.mapPostList(pages,queryWrapper,user.getUid());
    }

    @Override
    public void addCollection(AddCollectionForm request, AppUserEntity user) {
        Boolean collection = postCollectionService.isCollection(user.getUid(), request.getId());
        if(collection){
            throw new LinfengException("请勿重复点赞");
        }
        PostCollectionEntity pc=new PostCollectionEntity();
        pc.setPostId(request.getId());
        pc.setUid(user.getUid());
        postCollectionService.save(pc);
        //TODO 消息通知

    }

    @Override
    public AppPageUtils myPost(Integer page, AppUserEntity user) {
        QueryWrapper<PostEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PostEntity::getUid,user.getUid());
        queryWrapper.lambda().orderByDesc(PostEntity::getId);
        Page<PostEntity> pages = new Page<>(page,10);
        return this.mapPostList(pages, queryWrapper, user.getUid());
    }

    @Override
    public AppPageUtils myCollectPost(Integer page,AppUserEntity user) {
        List<Integer> postIdList=postCollectionService.getPostListByUid(user.getUid());
        if(postIdList.size()==0){
            return new AppPageUtils(null, 0, 10, 1);
        }
        QueryWrapper<PostEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().in(PostEntity::getId,postIdList);
        queryWrapper.lambda().orderByDesc(PostEntity::getId);
        Page<PostEntity> pages = new Page<>(page,10);
        return this.mapPostList(pages, queryWrapper, user.getUid());
    }

    @Override
    public PostDetailResponse detail(Integer id) {
        PostEntity post = this.getById(id);
        if(ObjectUtil.isNull(post)){
            throw new LinfengException("该帖子不存在或已删除");
        }

        AppUserEntity user = localUser.getUser();

        post.setReadCount(post.getReadCount()+1);
        baseMapper.updateById(post);
        PostDetailResponse response=new PostDetailResponse();
        BeanUtils.copyProperties(post,response);
        AppUserEntity userInfo = appUserService.getById(post.getUid());

        response.setUserInfo(userInfo);

        if(ObjectUtil.isNull(user)){
            response.setIsFollow(false);
            response.setIsCollection(false);
        }else{
            response.setIsFollow(followService.isFollowOrNot(user.getUid(), post.getUid()));
            response.setIsCollection(postCollectionService.isCollection(user.getUid(),post.getId()));
        }
        response.setCollectionCount(postCollectionService.collectCount(post.getId()));
        response.setCommentCount(commentService.getCountByPostId(post.getId()));
        response.setMedia(JsonUtils.JsonToList(post.getMedia()));//文件处理

        return response;
    }
    /**
     * 组装帖子分页
     * 在一个循环里 尽量减少数据库查询操作 这种方式并不太好 应该全部查询出来后再set值
     *
     * @param page
     * @param queryWrapper
     * @param uid
     * @return
     */
    public AppPageUtils  mapPostList(Page<PostEntity> page,QueryWrapper<PostEntity> queryWrapper,Integer uid){
        Page<PostEntity> pages = baseMapper.selectPage(page,queryWrapper);
        AppPageUtils appPage=new AppPageUtils(pages);
        List<PostEntity> data = (List<PostEntity>) appPage.getData();
        List<PostListResponse> responseList=new ArrayList<>();
        data.forEach(l->{
            PostListResponse response=new PostListResponse();
            BeanUtils.copyProperties(l,response);
            response.setCollectionCount(postCollectionService.collectCount(response.getId()));
            response.setCommentCount(commentService.getCountByTopicId(response.getId()));
            response.setUserInfo(appUserService.getById(response.getUid()));
            if (uid==0){
                response.setIsCollection(false);
            }else{
                response.setIsCollection(postCollectionService.isCollection(uid,response.getId()));
            }
            response.setMedia(JsonUtils.JsonToList(l.getMedia()));
            responseList.add(response);
        });
        appPage.setData(responseList);
        return appPage;
    }

}