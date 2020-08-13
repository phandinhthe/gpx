package com.terry.karros.gpx.demo.api;

import com.terry.karros.gpx.demo.dto.detail.GPXDetailResponse;
import com.terry.karros.gpx.demo.dto.recent.GPXRecentListResponse;
import com.terry.karros.gpx.demo.service.GPXService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/gpx")
public class GPXController {
    private final GPXService service;

    public GPXController(GPXService service) {
        this.service = service;
    }

    @GetMapping
    public GPXRecentListResponse list(
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit
            , @RequestParam(value = "offset", defaultValue = "0", required = false) int offset) {
        return service.list(limit, offset);
    }

    @GetMapping(path = "/{gpx_id}")
    public GPXDetailResponse getDetail(@PathVariable("gpx_id") long id) {
        return service.get(id);
    }
}
