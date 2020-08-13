package com.terry.karros.gpx.demo.exception;

import com.terry.karros.gpx.demo.GPXMessageResource;
import com.terry.karros.gpx.demo.aspect.LoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GPXExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GPXExceptionHandler.class);
    private final GPXMessageResource resource;
    private final Object[] EMPTY_ARRAY = new Object[]{};

    public GPXExceptionHandler(GPXMessageResource resource) {
        this.resource = resource;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GPXErrorResponse handle(Exception exception) {
        log.error(stacktrace(exception));
        GPXErrorResponse errorResponse = new GPXErrorResponse();
        errorResponse.setErrorCode(resource.getCode("error.server.api"));
        errorResponse.setErrorMsg(resource.getMsg("error.server.api", EMPTY_ARRAY));
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GPXErrorResponse handle(MethodArgumentTypeMismatchException exception) {
        log.error(stacktrace(exception));
        GPXErrorResponse errorResponse = new GPXErrorResponse();
        errorResponse.setErrorCode(resource.getCode("error.input.value.missmatch"));
        errorResponse.setErrorMsg(resource.getMsg("error.input.value.missmatch", new Object[]{exception.getValue()}));
        return errorResponse;
    }

    @ExceptionHandler(GPXException.class)
    public ResponseEntity<GPXErrorResponse> handle(GPXException gpxException) {
        log.error(stacktrace(gpxException));
        GPXErrorResponse errorResponse = new GPXErrorResponse();
        errorResponse.setErrorCode(getCode(gpxException));
        errorResponse.setErrorMsg(getMsg(gpxException));
        return ResponseEntity.status(getHttpStatus(gpxException)).body(errorResponse);
    }

    private String getMsg(GPXException gpxException) {
        return resource.getMsg(gpxException.getKey(), gpxException.getArgs());
    }

    private String getCode(GPXException gpxException) {
        return resource.getCode(gpxException.getKey());
    }

    private int getHttpStatus(GPXException gpxException) {
        return Integer.parseUnsignedInt(resource.getHttpStatus(gpxException.getKey()));
    }

    private String stacktrace(Exception exception) {
        return exception +
                Arrays.stream(exception.getStackTrace())
                .map(Objects::toString)
                .collect(Collectors.joining("\n      "));
    }
}
