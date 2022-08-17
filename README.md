# linfeng-community

#### 1.介绍
社交论坛uniapp**小程序/H5版本**基于SpringBoot2+MybatisPlus+Shiro+jwt+Redis+Vue+Uniapp的前后端分离的社交论坛问答发帖/BBS项目 项目分为Uniapp用户端（兼容H5和微信小程序版本）和Vue后台管理端（包括完整的权限处理）， 基于以下技术栈开发：SpringBoot2、MybatisPlus、Shiro、jwt、Redis、Vue、Uniapp、MySQL5.7。

功能：图文帖，短视频，圈子，私聊，微信支付（小程序/H5），付费贴，积分签到，钱包充值，积分余额兑换，话题标签,抽奖大转盘，手机号邮箱登录，人机发帖等完整功能，直接看演示更直观↓↓↓↓↓↓

<u>后台前端的代码在 src\main\resources\static\linfeng-community-vue目录下！</u>

<u>用户端的前端代码在 src\main\resources\static\linfeng-community-uniapp-ky 目录下！</u>

<u>代码三端已经全部开源，包括用户端也发布了开源版</u>

#### 2.软件演示

##### 2.1移动端H5演示版本（手机扫码浏览/输入手机号登录）或者 https://www.linfeng.tech  小程序效果演示和h5一致（除了登录不一样）

![移动端H5二维码](https://gitee.com/virus010101/linfeng-community/raw/master/images/er.png)

**后台管理系统**演示站点：

https://dev.linfeng.tech

演示账号： test       密码：123456

##### 2.2移动端效果截图

<img src="https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic11.png"/>



![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic12.png)



![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic13.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic14.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic15.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic16.png)

##### 2.3后台管理端效果截图

后台管理系统演示站点：

https://dev.linfeng.tech

演示账号： test       

密码：123456

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic05.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic25.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic06.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic07.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic08.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic09.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic10.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic03.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic21.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic22.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic23.png)

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic24.png)



#### 3.安装教程 

1.  配置数据库和redis。先启动redis，再启动后端api服务
2.  数据库请使用MySQL5.7，其他版本可能会有问题。sql文件请点个star后再加群获取
3.  配置后台前端  先npm install 下载依赖后，再npm run dev即可
4.  用户端uniapp代码  先npm install 安装依赖后，再启动项目
5.  具体配置可以参考标准版的文档

**林风社交论坛项目文档地址**：https://www.kancloud.cn/linfengkj/linfeng_community/2754703



项目地址：

https://gitee.com/virus010101/linfeng-community

https://github.com/virus010101/linfeng-community

#### 4.必看说明

1.**后台前端的代码**在 **src\main\resources\static\linfeng-community-vue** 目录下！

2.**用户端的前端代码**在 **src\main\resources\static\linfeng-community-uniapp-ky** 目录下！

3.**代码已经全部开源，包括用户端也发布了开源版**。演示站点的是标准版，不是开源版。

**标准版和开源版的区别**：

http://pic.linfeng.tech/test/20220816/22d8a6fa4ae845e1b58914a96acec6b7.png

http://pic.linfeng.tech/test/20220727/e472fbef0a8f412b912bea6fa5317acf.png

4.SQL文件开源的，在QQ群，开源不易，**请左上角star后备注gitee的用户名加QQ群（640700429）获取**

<img src="https://gitee.com/virus010101/linfeng-community/raw/master/images/qrcode.jpg" style="zoom:25%;" />

其他事项咨询可以如下扫码添加技术客服qq：

<img src="https://gitee.com/virus010101/linfeng-community/raw/master/images/mine.jpg" style="zoom: 25%;" />

#### 5.标准版更新记录

***当前版本V1.4.1***

![](https://gitee.com/virus010101/linfeng-community/raw/master/images/showPic27.png)

<u>2022.8.16</u>

###### **V1.4.1发布**

【新增】1.新增积分抽奖大转盘 [重磅]

【新增】2.新增圈子内帖子置顶功能 与平台置顶区分 [重磅]

【新增】3.新增客服页面 [重磅]

【新增】4.新增邮箱验证码登录 可配置开关 [重磅]

【新增】5.H5端新增注册页面 区分登录和注册  [重磅]

【优化】6.圈子管理员h5端删帖优化

【优化】7.h5端设置管理员页面样式兼容和用户列表图标

【优化】8.帖子详情查询优化

【优化】9.投票贴空选项优化

【优化】10.fastjson升级

【优化】11.后端swagger文档全注释

【优化】12.用户缓存信息

【优化】13.手机验证码登录

<u>2022.7.24</u>

###### **V1.4.0发布**

【新增】1.虚拟用户自动注册发帖 *【重磅】*  

【新增】2.后台统一管理可配置项设置 *【重磅】*

【新增】3.后台管理系统首页增加echarts图和快捷入口 *【重磅】*

【新增】4.个人中心菜单在管理端可配置化 *【重磅】*

【新增】5.轮播图新增跳转链接包括外链和页面链接

【新增】6.新增帖子上下架系统消息通知的处理

【新增】7.新增圈子页公告栏通知

【优化】8.后台管理系统添加敏感操作日志记录

【优化】9.h5端登录页面重构升级 *【重磅】*

【修复】10.修复帖子列表由于置顶帖导致分页查询不全的问题

【修复】11.修复圈子解散和删除存在的bug

<u>2022.7.1</u>

###### **V1.3.2发布**

1.新增投票贴

2.私聊模块前端样式重构升级

3.系统通知消息统一管理

4.修复苹果机型无法格式化时间的问题

5.修复瀑布流模式下h5可能存在的不兼容

<u>2022.6.8</u>

###### **V1.3.1发布**

1.修复移动端分页功能存在的底部提示bug

2.修复圈子管理员修改圈子背景图无效的bug

3.修复圈子人数展示不统一的问题

4.修复一键已读为空时的异常报错

5.优化圈子查询代码

6.优化帖子分页查询组装代码

7.新增子评论回复时间展示

8.新增帖子列表组件置顶图标展示属性

9.新增发帖时选择话题

10.发帖时间改为新格式

11.新增帖子瀑布流展示风格组件

<u>2022.5.14</u>

###### **V1.3.0发布**

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

###### **V1.2.2发布**

1.修复H5端回复评论bug； 

2.修复H5端分享图标布局； 

3.修复H5登录同步圈子人数；

4.优化H5端圈子页布局； 

5.优化无效token处理； 

6.新增H5端删除个人帖子选项；

<u>2022.04.17</u>

###### **V1.2.1发布**

1.新增防止重复提交注解

2.扩大匿名访问权限

3.优化小程序登录

4.新增后台前端面板统计数据

5.移动端用户手机号脱敏处理

6.新增帖子上下架处理



<u>2022.04.04</u>

###### **V1.2.0发布**

 1.优化私聊模块

 2.新增置顶功能

 3.优化UI 

4.优化消息已读未读的处理 

5.其他bug修复



<u>2022.03.05</u>

###### **V1.1发布**

移动端适配H5

移动端分享功能优化

移动端其他bug修复



<u>2022.01.29</u>

###### **V1.0发布**

