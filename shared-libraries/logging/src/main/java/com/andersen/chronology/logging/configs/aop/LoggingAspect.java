package com.andersen.chronology.logging.configs.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void services() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositories() {
    }

    @Around("services() || repositories()")
    public Object aroundServicesAndRepositories(final ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        final Object result;
        final String methodName = proceedingJoinPoint.getSignature().getName();
        final String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        final Object[] args = proceedingJoinPoint.getArgs();
        log.debug("Called method '{}.{}' with arguments: {}", className, methodName, args);
        try {
            result = proceedingJoinPoint.proceed();
            log.debug("Method '{}.{}' returned value: {}", className, methodName, result);
        } catch (final Throwable e) {
            log.error("Error occurred during method '{}.{}' call: {}", className, methodName,
                    e.getMessage());
            throw e;
        }
        return result;
    }
}
