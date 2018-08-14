package com.ufukuzun.projectdiff.api.dto;

import com.ufukuzun.projectdiff.api.test.builder.dto.DataDifferenceBuilder;
import com.ufukuzun.projectdiff.api.test.builder.dto.DataDifferencesResponseBuilder;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataDifferencesResponseTest {

    @Test
    public void shouldReturnFalseWhenHasNoDifference() {
        DataDifferencesResponse dataDifferencesResponse = DataDifferencesResponseBuilder.aDataDifferencesResponse().build();

        assertFalse(dataDifferencesResponse.hasDifferences());
    }

    @Test
    public void shouldReturnTrueWhenHasDifferences() {
        DataDifferencesResponse dataDifferencesResponse = DataDifferencesResponseBuilder.aDataDifferencesResponse()
                .differences(Collections.singletonList(DataDifferenceBuilder.aDataDifference().build()))
                .build();

        assertTrue(dataDifferencesResponse.hasDifferences());
    }

}