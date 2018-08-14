package com.ufukuzun.projectdiff.api.service;

import com.ufukuzun.projectdiff.api.domain.Data;
import com.ufukuzun.projectdiff.api.dto.DataCollectRequest;
import com.ufukuzun.projectdiff.api.test.base.BaseServiceIntegrationTest;
import com.ufukuzun.projectdiff.api.test.builder.domain.DataBuilder;
import com.ufukuzun.projectdiff.api.test.builder.dto.DataCollectRequestBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class DataCollectServiceTest extends BaseServiceIntegrationTest {

    @Autowired
    private DataCollectService dataCollectService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldSaveOlderVersionOfDataFirstTime() {
        DataCollectRequest dataCollectRequest = DataCollectRequestBuilder.aDataCollectRequest().data("aGlyZSBtZQ==").build();

        dataCollectService.saveOlderVersion("1", dataCollectRequest);

        Data data = entityManager.find(Data.class, "1");
        assertThat(data.getId(), equalTo("1"));
        assertThat(data.getOlderContent(), equalTo("aGlyZSBtZQ=="));
        assertThat(data.getNewerContent(), nullValue());
    }

    @Test
    public void shouldSaveNewerVersionOfDataFirstTime() {
        DataCollectRequest dataCollectRequest = DataCollectRequestBuilder.aDataCollectRequest().data("eW91IHdpbGwgbm90IHJlZ3JldCA6KQ==").build();

        dataCollectService.saveNewerVersion("1", dataCollectRequest);

        Data data = entityManager.find(Data.class, "1");
        assertThat(data.getId(), equalTo("1"));
        assertThat(data.getOlderContent(), nullValue());
        assertThat(data.getNewerContent(), equalTo("eW91IHdpbGwgbm90IHJlZ3JldCA6KQ=="));
    }

    @Test
    public void shouldUpdateOlderVersionOfExistingData() {
        Data previousData = DataBuilder.aData().id("1").olderContent(null).newerContent("eW91IHdpbGwgbm90IHJlZ3JldCA6KQ==").build();
        entityManager.persist(previousData);

        DataCollectRequest dataCollectRequest = DataCollectRequestBuilder.aDataCollectRequest().data("aGlyZSBtZQ==").build();

        dataCollectService.saveOlderVersion("1", dataCollectRequest);

        Data data = entityManager.find(Data.class, "1");
        assertThat(data.getId(), equalTo("1"));
        assertThat(data.getOlderContent(), equalTo("aGlyZSBtZQ=="));
        assertThat(data.getNewerContent(), equalTo("eW91IHdpbGwgbm90IHJlZ3JldCA6KQ=="));
    }

    @Test
    public void shouldUpdateNewerVersionOfExistingData() {
        Data previousData = DataBuilder.aData().id("1").olderContent("aGlyZSBtZQ==").newerContent(null).build();
        entityManager.persist(previousData);

        DataCollectRequest dataCollectRequest = DataCollectRequestBuilder.aDataCollectRequest().data("eW91IHdpbGwgbm90IHJlZ3JldCA6KQ==").build();

        dataCollectService.saveNewerVersion("1", dataCollectRequest);

        Data data = entityManager.find(Data.class, "1");
        assertThat(data.getId(), equalTo("1"));
        assertThat(data.getOlderContent(), equalTo("aGlyZSBtZQ=="));
        assertThat(data.getNewerContent(), equalTo("eW91IHdpbGwgbm90IHJlZ3JldCA6KQ=="));
    }

}