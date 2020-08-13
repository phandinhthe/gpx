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

import static com.terry.karros.gpx.demo.controller.GpxUploadControllerTest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DetailGpxControllerTest extends GpxDemoApplicationTests {
    @Before
    public void clear() {
        repository.deleteAll();
    }

    @Test
    public void testGetDetailGPX_AfterUploadFileSuccessful_ShouldReturnOK() throws Exception {
        String createdJson = mvc.perform(
                MockMvcRequestBuilders.multipart(API_GPX_UPLOAD)
                        .file(UPLOAD_FILE_PARAM, getFile("detail_sample.gpx")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        GPXUploadResponse createdResponse = toObject(createdJson, GPXUploadResponse.class);

        String detailJson = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/gpx".concat("/").concat(createdResponse.getId() + ""))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        GPXDetailResponse detailResponse = toObject(detailJson, GPXDetailResponse.class);
        Assert.assertEquals("Test Name", detailResponse.getMetadata().getName());
        Assert.assertEquals("test description", detailResponse.getMetadata().getDescription());
        Assert.assertEquals("http://www.oruxmaps.com", detailResponse.getMetadata().getLinkResponses().get(0).getHref());
        Assert.assertEquals("link text", detailResponse.getMetadata().getLinkResponses().get(0).getText());
        Assert.assertEquals(2, detailResponse.getWayPoints().size());
        Assert.assertEquals(2, detailResponse.getTracks().get(0).getSegments().get(0).getTrackPoints().size());
    }

    @Test
    public void testGetDetailGPX_WhenGPXNotExist_ShouldReturnBadRequest() throws Exception {
        String json = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/gpx".concat("/10000"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        GPXErrorResponse response = toObject(json, GPXErrorResponse.class);
        Assert.assertEquals("ERROR.API.004", response.getErrorCode());
        Assert.assertEquals("There is no GPX file with id \"10000\"", response.getErrorMsg());
    }

    @Test
    public void testGetDetailGPX_WhenMethodArguementIsInvalid_ShouldReturnBadRequest() throws Exception {
        String json = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/gpx".concat("/aaa"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        GPXErrorResponse response = toObject(json, GPXErrorResponse.class);
        Assert.assertEquals("ERROR.API.006", response.getErrorCode());
        Assert.assertEquals("Value \"aaa\" is invalid.", response.getErrorMsg());
    }
}
