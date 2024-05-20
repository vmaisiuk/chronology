package com.andersen.chronology.exception.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@Order(1)
@RequiredArgsConstructor
public class RetryAspect {

    private final RetryTemplate retryTemplate;

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object retryOnOptimisticLockException(ProceedingJoinPoint joinPoint) throws Throwable {
        return retryTemplate.execute(context -> {
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
            log.warn("Handling exception during method call '{}.{}'", className, methodName);
            return joinPoint.proceed();
        });
    }
}