package com.terry.karros.gpx.demo.service;

import com.terry.karros.gpx.demo.exception.GPXExceptionHelper;
import io.jenetics.jpx.GPX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class GPXParserUtils {

    private static final Logger log = LoggerFactory.getLogger(GPXParserUtils.class);

    public static GPX parse(MultipartFile file) {
        return toGPX(file);
    }

    private static GPX toGPX(MultipartFile file) {
        GPX gpx;
        try {
            gpx = GPX.read(file.getInputStream());
        } catch (IOException exception) {
            log.debug(exception.getMessage());
            throw GPXExceptionHelper.getInstance().invalidFormat(exception, file.getName());
        }
        return gpx;
    }
}
