package com.terry.karros.gpx.demo.controller;

import com.terry.karros.gpx.demo.GpxDemoApplicationTests;
import com.terry.karros.gpx.demo.dto.detail.GPXDetailResponse;
import com.terry.karros.gpx.demo.dto.recent.GPXRecentListResponse;
import com.terry.karros.gpx.demo.dto.upload.GPXUploadResponse;
import com.terry.karros.gpx.demo.exception.GPXErrorResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

import static com.terry.karros.gpx.demo.controller.GpxUploadControllerTest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecentGpxControllerTest extends GpxDemoApplicationTests {
    @Before
    public void clear() {
        repository.deleteAll();
    }

    @Test
    public void testGetRecentGPX_WhenMultipleGPXsAreUploaded_ShouldReturnAll() throws Exception {
        uploadFiles(List.of("recent_sample_1.gpx", "recent_sample_2.gpx", "recent_sample_3.gpx"));

        String json = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/gpx")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        GPXRecentListResponse response = toObject(json, GPXRecentListResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(3, response.getGpxResponses().size());
        Assert.assertEquals("Test Name 3", response.getGpxResponses().get(0).getMetadata().getName());
        Assert.assertEquals("Test Name 2", response.getGpxResponses().get(1).getMetadata().getName());
        Assert.assertEquals("Test Name 1", response.getGpxResponses().get(2).getMetadata().getName());
    }

    @Test
    public void testGetRecentGPXFirstPageLimitTwo_WhenMultipleGPXsAreUploaded_ShouldReturnAll() throws Exception {
        uploadFiles(List.of("recent_sample_1.gpx", "recent_sample_2.gpx", "recent_sample_3.gpx",
                "recent_sample_1.gpx", "recent_sample_2.gpx", "recent_sample_3.gpx"));

        String json = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/gpx")
                        .queryParam("page", "0")
                        .queryParam("limit", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        GPXRecentListResponse response = toObject(json, GPXRecentListResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(2, response.getGpxResponses().size());
        Assert.assertEquals("Test Name 3", response.getGpxResponses().get(0).getMetadata().getName());
        Assert.assertEquals("Test Name 2", response.getGpxResponses().get(1).getMetadata().getName());
    }

    @Test
    public void testGetRecentGPX_WhenNoGPXsAreUploaded_ShouldReturnEmpty() throws Exception {
        String json = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/gpx")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        GPXRecentListResponse response = toObject(json, GPXRecentListResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals("{}", json);
    }

    @Test
    public void testGetRecentGPX_WhenArgumentInvalid_ShouldReturnBadRequest() throws Exception {
        String json = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/gpx")
                        .queryParam("page", "a")
                        .queryParam("limit", "b")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        GPXErrorResponse response = toObject(json, GPXErrorResponse.class);
        Assert.assertEquals("ERROR.API.006", response.getErrorCode());
    }

    private void uploadFiles(List<String> fileNames) throws Exception {
        if (CollectionUtils.isEmpty(fileNames)) {
            return;
        }

        for (String fileName : fileNames) {
            mvc.perform(
                    MockMvcRequestBuilders.multipart(API_GPX_UPLOAD)
                            .file(UPLOAD_FILE_PARAM, getFile(fileName)).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        }
    }
}
