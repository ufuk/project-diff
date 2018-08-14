package com.ufukuzun.projectdiff.api.service;

import com.ufukuzun.projectdiff.api.domain.Data;
import com.ufukuzun.projectdiff.api.dto.DataDifferencesResponse;
import com.ufukuzun.projectdiff.api.test.base.BaseServiceIntegrationTest;
import com.ufukuzun.projectdiff.api.test.builder.domain.DataBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DataDifferenceServiceTest extends BaseServiceIntegrationTest {

    @Autowired
    private DataDifferenceService dataDifferenceService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldReturnNoDifferenceWhenNewerAndOlderContentsAreEmpty() {
        Data data = DataBuilder.aData()
                .id("1")
                .olderContent("")
                .newerContent("")
                .build();

        entityManager.persist(data);

        DataDifferencesResponse dataDifferencesResponse = dataDifferenceService.getDataDifferencesFor("1");

        assertFalse(dataDifferencesResponse.hasDifferences());
        assertTrue(dataDifferencesResponse.isEqualLengths());
        assertThat(dataDifferencesResponse.getDifferences(), empty());
    }

    @Test
    public void shouldReturnNoDifferenceWhenNewerAndOlderContentsAreSame() {
        Data data = DataBuilder.aData()
                .id("1")
                .olderContent("aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ==")
                .newerContent("aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ==")
                .build();

        entityManager.persist(data);

        DataDifferencesResponse dataDifferencesResponse = dataDifferenceService.getDataDifferencesFor("1");

        assertFalse(dataDifferencesResponse.hasDifferences());
        assertTrue(dataDifferencesResponse.isEqualLengths());
        assertThat(dataDifferencesResponse.getDifferences(), empty());
    }

    @Test
    public void shouldReturnNoDifferenceWhenOlderHasLongerContent() {
        Data data = DataBuilder.aData()
                .id("1")
                .olderContent("aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ==")
                .newerContent("aGlyZSBtZQ==")
                .build();

        entityManager.persist(data);

        DataDifferencesResponse dataDifferencesResponse = dataDifferenceService.getDataDifferencesFor("1");

        assertFalse(dataDifferencesResponse.hasDifferences());
        assertFalse(dataDifferencesResponse.isEqualLengths());
        assertThat(dataDifferencesResponse.getDifferences(), empty());
    }

    @Test
    public void shouldReturnNoDifferenceWhenNewerHasLongerContent() {
        Data data = DataBuilder.aData()
                .id("1")
                .olderContent("aGlyZSBtZQ==")
                .newerContent("aGlyZSBtZSwgeW91IHdpbGwgbm90IHJlZ3JldCA6KQ==")
                .build();

        entityManager.persist(data);

        DataDifferencesResponse dataDifferencesResponse = dataDifferenceService.getDataDifferencesFor("1");

        assertFalse(dataDifferencesResponse.hasDifferences());
        assertFalse(dataDifferencesResponse.isEqualLengths());
        assertThat(dataDifferencesResponse.getDifferences(), empty());
    }

    @Test
    public void shouldReturnDifferenceWhenNewerAndOlderContentsHaveDifferenceAndBothAreTheSameLength() {
        Data data = DataBuilder.aData()
                .id("1")
                .olderContent("a12bc")
                .newerContent("a34bc")
                .build();

        entityManager.persist(data);

        DataDifferencesResponse dataDifferencesResponse = dataDifferenceService.getDataDifferencesFor("1");

        assertTrue(dataDifferencesResponse.hasDifferences());
        assertTrue(dataDifferencesResponse.isEqualLengths());
        assertThat(dataDifferencesResponse.getDifferences(), hasSize(1));
        assertThat(dataDifferencesResponse.getDifferences().get(0).getOffset(), equalTo(1));
        assertThat(dataDifferencesResponse.getDifferences().get(0).getLength(), equalTo(2));
    }

    @Test
    public void shouldReturnMultipleDifferencesWhenNewerAndOlderContentsHaveMoreThanOneDifferences() {
        Data data = DataBuilder.aData()
                .id("1")
                .olderContent("12abcdef5gh")
                .newerContent("34abcdef6gh")
                .build();

        entityManager.persist(data);

        DataDifferencesResponse dataDifferencesResponse = dataDifferenceService.getDataDifferencesFor("1");

        assertTrue(dataDifferencesResponse.hasDifferences());
        assertTrue(dataDifferencesResponse.isEqualLengths());
        assertThat(dataDifferencesResponse.getDifferences(), hasSize(2));
        assertThat(dataDifferencesResponse.getDifferences().get(0).getOffset(), equalTo(0));
        assertThat(dataDifferencesResponse.getDifferences().get(0).getLength(), equalTo(2));
        assertThat(dataDifferencesResponse.getDifferences().get(1).getOffset(), equalTo(8));
        assertThat(dataDifferencesResponse.getDifferences().get(1).getLength(), equalTo(1));
    }

}