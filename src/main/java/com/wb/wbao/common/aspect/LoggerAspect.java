package com.wb.wbao.common.aspect;

import com.wb.wbao.common.annotation.Loggable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Method method = null;
        for (Method mt : targetClass.getMethods()) {
            if (methodName.equals(mt.getName())) {
                method = mt;
                break;
            }
        }

        Loggable loggable = method.getAnnotation(Loggable.class);
        if(Objects.isNull(loggable)){
            return;
        }

        logger.info("loggable desc:{}, optType:{}, module:{},params:{}", loggable.describe(), loggable.optType(), loggable.module(), params);
        //loggable desc:登录, optType:POST, module:LOGIN,params:[User{loginName='wangbao', password='wangbao'}
    }

    @AfterThrowing(value = "log()", throwing = "ex")
    public void log(JoinPoint joinPoint, Throwable ex) {
        // 获取参数
        Object[] params = joinPoint.getArgs();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Method method = null;
        for (Method mt : targetClass.getMethods()) {
            if (methodName.equals(mt.getName())) {
                method = mt;
                break;
            }
        }

        Loggable loggable = method.getAnnotation(Loggable.class);
        if(Objects.isNull(loggable)){
            return;
        }

        logger.info("loggable desc:{}, optType:{}, module:{}, exception:{}, params:{}", loggable.describe(), loggable.optType(), loggable.module(), ex.getMessage(),  params);
        //loggable desc:登录, optType:POST, module:LOGIN, exception:/ by zero, params:[User{loginName='wangbao', password='wangbao'}
    }
}
