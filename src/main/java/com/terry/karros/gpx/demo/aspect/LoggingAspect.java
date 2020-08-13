package com.terry.karros.gpx.demo.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terry.karros.gpx.demo.dto.upload.GPXUploadResponse;
import org.apache.logging.log4j.core.util.IOUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;

@Aspect
@Configuration
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.terry.karros.gpx.demo.api.*.*(..))")
    protected void loggingPublicOperation() {
    }

    @Before("loggingPublicOperation()")
    public void logBefore(JoinPoint joinPoint) throws Throwable {
        log.debug("Entering in Method :  " + joinPoint.getSignature().getName());
        log.debug("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        log.debug("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        log.debug("Target class : " + joinPoint.getTarget().getClass().getName());
        if (Objects.isNull(request)) {
            return;
        }
        log.debug(String.format("Path info: %s", request.getRequestURL().toString()));
        log.debug(String.format("HTTP Method: %s", request.getMethod()));
        log.debug(String.format("Query params: %s", request.getQueryString()));
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            Object headerName = headerNames.nextElement();
            if (Objects.isNull(headerName)) {
                continue;
            }
            String headerValue = request.getHeader(headerName.toString());
            log.debug(String.format("Header name/value: {%s, %s}", headerName.toString(), headerValue));
        }
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            Object paramName = paramNames.nextElement();
            if (Objects.isNull(paramName)) {
                continue;
            }
            String[] paramValue = request.getParameterValues(paramName.toString());
            log.debug(String.format("Param name/value: {%s, %s}", paramName.toString(), Arrays.toString(paramValue)));
        }
        log.debug(String.format("Request body: %s", IOUtils.toString(request.getReader())));
    }

    @AfterReturning(pointcut = "loggingPublicOperation()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        String returnValue = this.toJson(result);
        log.debug("Response: " + returnValue);

    }

    private  <T> String toJson(T request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
    }
}
