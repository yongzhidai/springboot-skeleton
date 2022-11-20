package com.dyz.skeleton.configuration.aspect;

import com.dyz.skeleton.common.ResultDto;
import com.dyz.skeleton.common.exception.BizException;
import com.dyz.skeleton.common.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class ResultAspect {

    @Pointcut("execution(public * com.dyz.skeleton.controller..*.*(..))")
    public void handlePlaceholder() {
    }

    @Around("handlePlaceholder()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {

            Object result = pjp.proceed();
            return result;
        }catch (ParamException e){
            return ResultDto.paramFail(e);
        }catch (BizException e){
            return ResultDto.bizFail(e);
        }catch (Exception e){
            log.error("服务器异常",e);
            return ResultDto.sysFail(e);
        }
    }
}