package com.ufukuzun.projectdiff.api.test.builder.dto;

import com.ufukuzun.projectdiff.api.dto.DataDifference;
import com.ufukuzun.projectdiff.api.dto.DataDifferencesResponse;

import java.util.ArrayList;
import java.util.List;

public final class DataDifferencesResponseBuilder {

    private List<DataDifference> differences = new ArrayList<>();

    private boolean equalLengths;

    private DataDifferencesResponseBuilder() {
    }

    public static DataDifferencesResponseBuilder aDataDifferencesResponse() {
        return new DataDifferencesResponseBuilder();
    }

    public DataDifferencesResponseBuilder differences(List<DataDifference> differences) {
        this.differences = differences;
        return this;
    }

    public DataDifferencesResponseBuilder equalLengths(boolean equalLengths) {
        this.equalLengths = equalLengths;
        return this;
    }

    public DataDifferencesResponse build() {
        DataDifferencesResponse dataDifferencesResponse = new DataDifferencesResponse();
        dataDifferencesResponse.setDifferences(differences);
        dataDifferencesResponse.setEqualLengths(equalLengths);
        return dataDifferencesResponse;
    }

}
