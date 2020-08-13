package com.terry.karros.gpx.demo.exception;

public class GPXException extends RuntimeException {
    private String key;
    private Object[] args;

    public GPXException(String code, Throwable cause, Object[] args) {
        super(cause);
        this.key = code;
        this.args = args;
    }

    public String getKey() {
        return key;
    }

    public Object[] getArgs() {
        return args;
    }
}
