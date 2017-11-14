READ: git is a good version control tool, it is very good

this project order to study spring boot and other technology about java language.

1、logback介绍
Logback是由log4j创始人设计的又一个开源日志组件。logback当前分成三个模块：
logback-core,logback- classic和logback-access。
logback-core是其它两个模块的基础模块。
logback-classic是log4j的一个 改良版本。
此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging。
logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能。
logback的配置文件都放在/src/main/resource/文件夹下的logback.xml文件中。其中logback.xml文件就是logback的配置文件。

2、shiro
 集成shiro大概分这么一个步骤：
(a) pom.xml中添加Shiro依赖；
(b) 注入Shiro Factory和SecurityManager。
(c) 身份认证
(d) 权限控制

3、多线程处理
自研并发任务调度器，任务进度轮询器

4、目前还欠缺的功能：
（1）Shiro的remember me功能、并发登录限制功能、验证码
（2）StringManager
（3）统一异常处理
（4）集成WebSocket
（5）任务台
（6）Header/左导航


TODO List
1、文件、文件夹列表展示
2、文件夹视图/文件视图切换
3、清空文件夹功能
4、文件上传下载（http、ftp、samba）