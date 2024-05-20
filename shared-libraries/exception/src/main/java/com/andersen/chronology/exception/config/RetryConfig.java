package com.andersen.chronology.exception.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

@Configuration
public class RetryConfig {

    @Value("${exception.retry:10}")
    private int numberOfAttempts;

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExceptionClassifierRetryPolicy retryPolicy = new ExceptionClassifierRetryPolicy();
        SimpleRetryPolicy optimisticLockRetryPolicy = new SimpleRetryPolicy();
        optimisticLockRetryPolicy.setMaxAttempts(numberOfAttempts);
        retryPolicy.setPolicyMap(Collections.singletonMap(
                ObjectOptimisticLockingFailureException.class, optimisticLockRetryPolicy));
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }
}
