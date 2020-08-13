package com.terry.karros.gpx.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Component
public class GPXMessageResource {
    private final MessageSource messageSource;
    private final AcceptHeaderLocaleResolver localResolver;

    @Autowired
    HttpServletRequest request;
    public GPXMessageResource(MessageSource messageSource, AcceptHeaderLocaleResolver localResolver) {
        this.messageSource = messageSource;
        this.localResolver = localResolver;
    }

    public String getCode(String key) {
        return messageSource.getMessage(key.concat(".code"), null, localResolver.resolveLocale(request));
    }

    public String getMsg(String key, Object[] objects) {
        return messageSource.getMessage(key.concat(".message"), objects, localResolver.resolveLocale(request));
    }

    public String getHttpStatus(String key) {
        return messageSource.getMessage(key.concat(".http.status"), null, localResolver.resolveLocale(request));
    }
}
