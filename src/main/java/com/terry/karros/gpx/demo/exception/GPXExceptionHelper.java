package com.terry.karros.gpx.demo.exception;

import java.util.Objects;

public class GPXExceptionHelper {
    private static GPXExceptionHelper instance;
    private static Object lock = new Object();

    private static final Object[] EMPTY_ARRAY = new Object[]{};

    private GPXExceptionHelper() {
    }

    public static GPXExceptionHelper getInstance() {
        if (!Objects.isNull(instance)) {
            return instance;
        }
        synchronized (lock) {
            if (Objects.isNull(instance)) {
                return new GPXExceptionHelper();
            }
            return instance;
        }
    }

    public GPXException serverError(Throwable cause) {
        return new GPXException("error.server.api", cause, EMPTY_ARRAY);
    }

    public GPXException invalidFormat(Throwable cause, Object ... objects) {
        return new GPXException("error.invalid.format.gpx",cause , objects);
    }

    public GPXException notFoundGPX(Throwable cause, Object ... objects) {
        return new GPXException("error.gpx.not.found", cause, objects);
    }
}
