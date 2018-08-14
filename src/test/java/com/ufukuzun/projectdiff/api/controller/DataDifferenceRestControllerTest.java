package com.ufukuzun.projectdiff.api.controller;

import com.ufukuzun.projectdiff.api.dto.DataDifference;
import com.ufukuzun.projectdiff.api.dto.DataDifferencesResponse;
import com.ufukuzun.projectdiff.api.service.DataDifferenceService;
import com.ufukuzun.projectdiff.api.test.base.BaseControllerIntegrationTest;
import com.ufukuzun.projectdiff.api.test.builder.dto.DataDifferenceBuilder;
import com.ufukuzun.projectdiff.api.test.builder.dto.DataDifferencesResponseBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DataDifferenceRestControllerTest extends BaseControllerIntegrationTest {

    @Autowired
    private DataDifferenceService dataDifferenceService;

    @Test
    public void shouldGetNewerVersionDifferencesOfData() throws Exception {
        DataDifference dataDifference1 = DataDifferenceBuilder.aDataDifference().offset(1).length(13).build();
        DataDifference dataDifference2 = DataDifferenceBuilder.aDataDifference().offset(32).length(2).build();
        DataDifferencesResponse dataDifferencesResponse = DataDifferencesResponseBuilder.aDataDifferencesResponse()
                .differences(Arrays.asList(dataDifference1, dataDifference2))
                .equalLengths(true)
                .build();

        when(dataDifferenceService.getDataDifferencesFor("1")).thenReturn(dataDifferencesResponse);

        getMockMvc().perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hasDifferences", equalTo(true)))
                .andExpect(jsonPath("hasEqualLengths", equalTo(true)))
                .andExpect(jsonPath("differences.*", hasSize(2)))
                .andExpect(jsonPath("differences[0].offset", equalTo(1)))
                .andExpect(jsonPath("differences[0].length", equalTo(13)))
                .andExpect(jsonPath("differences[1].offset", equalTo(32)))
                .andExpect(jsonPath("differences[1].length", equalTo(2)));

        verify(dataDifferenceService).getDataDifferencesFor("1");
    }

}