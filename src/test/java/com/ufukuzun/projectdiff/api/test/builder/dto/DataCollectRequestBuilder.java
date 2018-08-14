package com.ufukuzun.projectdiff.api.test.builder.dto;

import com.ufukuzun.projectdiff.api.dto.DataCollectRequest;

public final class DataCollectRequestBuilder {

    private String data;

    private DataCollectRequestBuilder() {
    }

    public static DataCollectRequestBuilder aDataCollectRequest() {
        return new DataCollectRequestBuilder();
    }

    public DataCollectRequestBuilder data(String data) {
        this.data = data;
        return this;
    }

    public DataCollectRequest build() {
        DataCollectRequest dataCollectRequest = new DataCollectRequest();
        dataCollectRequest.setData(data);
        return dataCollectRequest;
    }

}
