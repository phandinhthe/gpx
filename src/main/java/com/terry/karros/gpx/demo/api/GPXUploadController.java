package com.terry.karros.gpx.demo.api;

import com.terry.karros.gpx.demo.dto.upload.GPXUploadResponse;
import com.terry.karros.gpx.demo.service.GPXUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/gpx/upload")
public class GPXUploadController {

    private final GPXUploadService service;

    public GPXUploadController(GPXUploadService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public GPXUploadResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
        return service.store(file);
    }
}
