READ: git is a good version control tool, it is very good

this project order to study spring boot and other technology about java language.

1、logback介绍
Logback是由log4j创始人设计的又一个开源日志组件。logback当前分成三个模块：
logback-core,logback- classic和logback-access。
logback-core是其它两个模块的基础模块。
logback-classic是log4j的一个 改良版本。
此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统
如log4j或JDK14 Logging。
logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能。

logback的配置文件都放在/src/main/resource/文件夹下的logback.xml文件中。
其中logback.xml文件就是logback的配置文件。
只要将这个文件放置好了之后，系统会自动找到这个配置文件。
