package com.terry.karros.gpx.demo.service;

import com.terry.karros.gpx.demo.exception.GPXExceptionHelper;
import io.jenetics.jpx.GPX;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class GPXParserUtils {

    public static GPX parse(MultipartFile file) {
        return toGPX(file);
    }

    private static GPX toGPX(MultipartFile file) {
        GPX gpx;
        try {
            gpx = GPX.read(file.getInputStream());
        } catch (IOException exception) {
            throw GPXExceptionHelper.getInstance().invalidFormat(exception, file.getName());
        }
        return gpx;
    }
}
