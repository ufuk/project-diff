package com.ufukuzun.projectdiff.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DataDifferencesResponse {

    private List<DataDifference> differences = new ArrayList<>();

    private boolean equalLengths;

    @JsonProperty(value = "hasEqualLengths")
    public boolean isEqualLengths() {
        return equalLengths;
    }

    public void setEqualLengths(boolean equalLengths) {
        this.equalLengths = equalLengths;
    }

    public List<DataDifference> getDifferences() {
        return differences;
    }

    public void setDifferences(List<DataDifference> differences) {
        this.differences = differences;
    }

    public void addDifference(DataDifference dataDifference) {
        getDifferences().add(dataDifference);
    }

    @JsonProperty
    public boolean hasDifferences() {
        return !getDifferences().isEmpty();
    }

}
