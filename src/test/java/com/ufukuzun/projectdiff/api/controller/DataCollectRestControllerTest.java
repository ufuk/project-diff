package com.ufukuzun.projectdiff.api.controller;

import com.ufukuzun.projectdiff.api.dto.DataCollectRequest;
import com.ufukuzun.projectdiff.api.service.DataCollectService;
import com.ufukuzun.projectdiff.api.test.base.BaseControllerIntegrationTest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DataCollectRestControllerTest extends BaseControllerIntegrationTest {

    @Autowired
    private DataCollectService dataCollectService;

    @Captor
    private ArgumentCaptor<DataCollectRequest> dataCollectRequestArgumentCaptor;

    @Test
    public void shouldSaveOlderVersionOfData() throws Exception {
        getMockMvc().perform(post("/1/left")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"data\":\"aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ==\"}"))
                .andExpect(status().isOk());

        verify(dataCollectService).saveOlderVersion(eq("1"), dataCollectRequestArgumentCaptor.capture());

        DataCollectRequest dataCollectRequest = dataCollectRequestArgumentCaptor.getValue();
        assertThat(dataCollectRequest.getData(), equalTo("aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ=="));
    }

    @Test
    public void shouldSaveNewerVersionOfData() throws Exception {
        getMockMvc().perform(post("/1/right")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"data\":\"aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ==\"}"))
                .andExpect(status().isOk());

        verify(dataCollectService).saveNewerVersion(eq("1"), dataCollectRequestArgumentCaptor.capture());

        DataCollectRequest dataCollectRequest = dataCollectRequestArgumentCaptor.getValue();
        assertThat(dataCollectRequest.getData(), equalTo("aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ=="));
    }

}