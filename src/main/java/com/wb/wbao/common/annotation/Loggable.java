package com.wb.wbao.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 在使用Retention时必须要提供一个RetentionPolicy的枚举类型参数
// RetentionPolicy有三个枚举内容：CLASS RUNTIME SOURCE
// SOURCE, //编译程序处理完Annotation信息后就完成任务
// CLASS, //编译程序将Annotation储存于class档中，缺省
// RUNTIME //编译程序将Annotation储存于class当中，可由JVM读入(通过反射机制)。这个功能搭配反射是非常强大的
@Retention(RetentionPolicy.RUNTIME)
// @Target里面的ElementType是用来指定Annotation类型可以用在哪一些元素上的.说明一下：TYPE(类型), FIELD(属性),
// METHOD(方法), PARAMETER(参数), CONSTRUCTOR(构造函数),LOCAL_VARIABLE(局部变量),
// ANNOTATION_TYPE,PACKAGE(包),其中的TYPE(类型)是指可以用在Class,Interface,Enum和Annotation类型上.
@Target(ElementType.METHOD)
public @interface Loggable {

    /**
     * 操作类型4种（INSERT、UPDATE、SELECT、DELETE）
     */
    public String optType();

    /** 描述 */
    public String describe();

    /** 模块 */
    public String module();
}
