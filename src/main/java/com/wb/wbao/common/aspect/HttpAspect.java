package com.wb.wbao.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.wb.wbao.web.*.*(..))")
    public void log(){}

    /**
     * 进入Controller方法之前
     * @param joinPoint 切点
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        LOGGER.info("url={}", request.getRequestURL());
        LOGGER.info("method={}", request.getMethod());
        LOGGER.info("ip={}", request.getRemoteAddr());
        LOGGER.debug("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        LOGGER.info("args={}", joinPoint.getArgs());
    }

    /**
     * http请求返回前
     */
    @After("log()")
    public void doAfter(){
        LOGGER.info("http out");
    }

    /**
     * 获取返回值
     * @param o 返回值
     */
    @AfterReturning(returning = "o", pointcut = "log()")
    public void doAfterReturning(Object o){
        LOGGER.info("response={}", o);
    }


}
