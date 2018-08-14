package com.ufukuzun.projectdiff.api.test.builder.dto;

import com.ufukuzun.projectdiff.api.dto.DataDifference;

public final class DataDifferenceBuilder {

    private int offset;

    private int length;

    private DataDifferenceBuilder() {
    }

    public static DataDifferenceBuilder aDataDifference() {
        return new DataDifferenceBuilder();
    }

    public DataDifferenceBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    public DataDifferenceBuilder length(int length) {
        this.length = length;
        return this;
    }

    public DataDifference build() {
        DataDifference dataDifference = new DataDifference();
        dataDifference.setOffset(offset);
        dataDifference.setLength(length);
        return dataDifference;
    }

}
