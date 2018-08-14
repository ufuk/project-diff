package com.ufukuzun.projectdiff.api.test.builder.domain;

import com.ufukuzun.projectdiff.api.domain.Data;

public final class DataBuilder {

    private String id;

    private String olderContent;

    private String newerContent;

    private DataBuilder() {
    }

    public static DataBuilder aData() {
        return new DataBuilder();
    }

    public DataBuilder id(String id) {
        this.id = id;
        return this;
    }

    public DataBuilder olderContent(String olderContent) {
        this.olderContent = olderContent;
        return this;
    }

    public DataBuilder newerContent(String newerContent) {
        this.newerContent = newerContent;
        return this;
    }

    public Data build() {
        Data data = new Data();
        data.setId(id);
        data.setOlderContent(olderContent);
        data.setNewerContent(newerContent);
        return data;
    }

}
