# linfeng-community

#### 1.介绍
社交论坛uniapp**小程序/H5版本**基于SpringBoot2+MybatisPlus+Shiro+jwt+Redis+Vue+Uniapp的前后端分离的社交论坛问答发帖/BBS项目 项目分为Uniapp用户端（兼容H5和微信小程序版本）和Vue后台管理端（包括完整的权限处理）， 基于以下技术栈开发：SpringBoot2、MybatisPlus、Shiro、jwt、Redis、Vue、Uniapp、MySQL5.7。

功能：图文帖，短视频，圈子，私聊，微信支付（小程序/H5），付费贴，积分签到，钱包充值，积分余额兑换，话题标签等完整功能，直接看演示更直观↓↓↓↓↓↓

#### 2.软件演示

##### 2.1移动端H5演示版本（手机扫码浏览/输入手机号登录）或者 https://www.linfeng.tech  小程序效果演示和h5一致（除了登录不一样）

![移动端H5二维码](https://gitee.com/virus010101/linfeng-community/raw/master/images/er.png)

##### 2.2移动端效果截图

<img src="https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic11.png"/>



![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic12.png)



![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic13.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic14.png)

##### 2.3后台管理端效果截图

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic05.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic06.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic07.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic08.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic09.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic10.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic03.png)

#### 3.安装教程 

1.  配置数据库和redis。先启动redis，再启动后端api服务
2.  数据库请使用MySQL5.7，其他版本可能会有问题
3.  配置后台前端  先npm install 下载依赖后，再npm run dev即可

**林风社交论坛项目文档地址**：https://www.kancloud.cn/linfengkj/linfeng_community/2754703



项目地址：

https://gitee.com/virus010101/linfeng-community

https://github.com/virus010101/linfeng-community

#### 4.必看说明

1.**后台前端的代码**在 **src\main\resources\static\linfeng-community-vue** 目录下！

2.**后台管理系统和后台管理系统后端API已经全部开源**。

3.SQL文件开源的，在QQ群，开源不易，**请左上角star后备注gitee的用户名加QQ群（640700429）获取**

<img src="https://gitee.com/virus010101/linfeng-community/raw/master/images/qrcode.jpg" style="zoom:25%;" />

其他事项咨询可以如下扫码添加技术客服qq：

<img src="https://gitee.com/virus010101/linfeng-community/raw/master/images/mine.jpg" style="zoom: 25%;" />

#### 5.版本更新记录

当前版本V1.3.0       **V1.3.1即将发布**

<u>2022.5.14</u>

**V1.3（正式版）发布**

1.优化私聊功能及时性

2.优化阿里云短信验证码和测试阶段的处理

3.优化话题标签删除导致的问题

4.新增用户端圈子管理员圈内删贴功能

5.新增微信小程序端支付

6.新增私信列表历史消息

7.新增付费贴

8.新增积分签到

9.新增余额钱包体系&用户积分体系

10.新增可配置消息清理定时任务

11.新增用户端父子评论长按删除

12.新增管理端模糊查询

13.新增后台管理端用户余额充值

14.修复私信排序问题和查询可能存在的问题

15.修复quartz定时任务暂停和恢复的bug

16.新增h5微信支付

17.新增视频模块和充值模块显示配置

18.新增积分兑换余额模块

<u>2022.4.24</u>

**V1.2.2发布**

1.修复H5端回复评论bug； 

2.修复H5端分享图标布局； 

3.修复H5登录同步圈子人数；

4.优化H5端圈子页布局； 

5.优化无效token处理； 

6.新增H5端删除个人帖子选项；

<u>2022.04.17</u>

**V1.2.1发布**

1.新增防止重复提交注解

2.扩大匿名访问权限

3.优化小程序登录

4.新增后台前端面板统计数据

5.移动端用户手机号脱敏处理

6.新增帖子上下架处理



<u>2022.04.04</u>

**V1.2发布**

 1.优化私聊模块

 2.新增置顶功能

 3.优化UI 

4.优化消息已读未读的处理 

5.其他bug修复



<u>2022.03.05</u>

**V1.1发布**

移动端适配H5

移动端分享功能优化

移动端其他bug修复



<u>2022.01.29</u>

**V1.0发布**

