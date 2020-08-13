package com.terry.karros.gpx.demo.exception;

import com.terry.karros.gpx.demo.GPXMessageResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GPXExceptionHandler {
    private final GPXMessageResource resource;
    private final Object[] EMPTY_ARRAY = new Object[]{};

    public GPXExceptionHandler(GPXMessageResource resource) {
        this.resource = resource;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GPXErrorResponse handle(RuntimeException runtimeException) {
        GPXErrorResponse errorResponse = new GPXErrorResponse();
        errorResponse.setErrorCode(resource.getCode("error.server.api"));
        errorResponse.setErrorMsg(resource.getMsg("error.server.api", EMPTY_ARRAY));
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GPXErrorResponse handle(MethodArgumentTypeMismatchException runtimeException) {
        GPXErrorResponse errorResponse = new GPXErrorResponse();
        errorResponse.setErrorCode(resource.getCode("error.input.value.missmatch"));
        errorResponse.setErrorMsg(resource.getMsg("error.input.value.missmatch", new Object[]{runtimeException.getValue()}));
        return errorResponse;
    }

    @ExceptionHandler(GPXException.class)
    public ResponseEntity<GPXErrorResponse> handle(GPXException gpxException) {
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
}
