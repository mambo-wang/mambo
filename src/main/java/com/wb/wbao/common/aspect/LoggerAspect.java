package com.wb.wbao.common.aspect;

import com.wb.wbao.common.annotation.Loggable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component(value = "loggerAspect")
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(public * com.wb.wbao.web.*.*(..)) && @annotation(com.wb.wbao.common.annotation.Loggable)")
    public void log(){
    }

    @AfterReturning(value = "log()", returning = "retVal")
    public void log(JoinPoint joinPoint, Object retVal) {
        // 获取参数
        Object[] params = joinPoint.getArgs();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        Class<?> tagetClass = joinPoint.getTarget().getClass();
        Method method = null;
        for (Method mt : tagetClass.getMethods()) {
            if (methodName.equals(mt.getName())) {
                method = mt;
                break;
            }
        }

        if(Objects.isNull(method)){
            return;
        }
        Loggable loggable = method.getAnnotation(Loggable.class);
        if(Objects.isNull(loggable)){
            return;
        }

        logger.info("loggable desc:{}, optType:{}, module:{}", loggable.describe(), loggable.optType(), loggable.module());


    }

}
