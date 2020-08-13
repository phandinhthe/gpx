package com.terry.karros.gpx.demo.controller;

import com.terry.karros.gpx.demo.GpxDemoApplicationTests;
import com.terry.karros.gpx.demo.dto.upload.GPXUploadResponse;
import com.terry.karros.gpx.demo.exception.GPXErrorResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GpxUploadControllerTest extends GpxDemoApplicationTests {

    public static final String API_GPX_UPLOAD = "/api/v1/gpx/upload";
    private static final String CLASSPATH_UPLOAD = "classpath:upload/";
    public static final String UPLOAD_FILE_PARAM = "file";

    public static byte[] getFile(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(ResourceUtils.getFile(CLASSPATH_UPLOAD.concat(fileName)));
        return FileCopyUtils.copyToByteArray(inputStream);
    }

    @Test
    public void testUploadGPX_WhenGPXFileIsOK_ShouldReturnCreated() throws Exception {
        String json = mvc.perform(
                MockMvcRequestBuilders.multipart(API_GPX_UPLOAD)
                        .file(UPLOAD_FILE_PARAM, getFile("sample.gpx")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        GPXUploadResponse response = toObject(json, GPXUploadResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), 1l);
    }

    @Test
    public void testUploadGPX_WhenGPXFileIsInvalid_ShouldReturnBadRequest() throws Exception {
        String json = mvc.perform(
                MockMvcRequestBuilders.multipart(API_GPX_UPLOAD)
                        .file("file", getFile("invalid-format-sample.gpx"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        GPXErrorResponse response = toObject(json, GPXErrorResponse.class);
        Assert.assertEquals("ERROR.API.002", response.getErrorCode());
        Assert.assertEquals("The format of uploaded file is invalid!"
                , response.getErrorMsg());
    }

    @Test
    public void testUploadGPX_WhenGPXFileIsEmpty_ShouldReturnBadRequest() throws Exception {
        String json = mvc.perform(
                MockMvcRequestBuilders.multipart(API_GPX_UPLOAD)
                        .file("file", getFile("empty-file.gpx"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        GPXErrorResponse response = toObject(json, GPXErrorResponse.class);
        Assert.assertEquals("ERROR.API.002", response.getErrorCode());
        Assert.assertEquals("The format of uploaded file is invalid!", response.getErrorMsg());
    }
}
