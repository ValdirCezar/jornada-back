package com.valdir.jornadaback.advices;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.time.LocalDateTime.now;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut("within(com.valdir.jornadaback.resources..*) " +
            "|| within(com.valdir.jornadaback.services..*)" +
            "|| within(com.valdir.jornadaback.security.JWTAuthorizationFilter)")
    public void myPointcut() { }

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getSimpleName();
        Object[] array = pjp.getArgs();
        log.info("MOMENT: " + now() + ", CLASS INVOKED: " + className + ", METHOD: " + methodName + "()" + ", ARGUMENTS: "
                + mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info("MOMENT : " + now() + ", CLASS : " + className + ", METHOD : " + methodName + "()" + ", RESPONSE : "
                + mapper.writeValueAsString(object));
        return object;
    }

}