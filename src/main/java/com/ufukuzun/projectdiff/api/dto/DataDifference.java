package com.ufukuzun.projectdiff.api.dto;

public class DataDifference {

    private int offset;

    private int length;

    public DataDifference() {
    }

    public DataDifference(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void incrementLength() {
        this.length++;
    }

}
