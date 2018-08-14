package com.ufukuzun.projectdiff.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Data {

    @Id
    private String id;

    @Lob
    private String olderContent;

    @Lob
    private String newerContent;

    public Data() {
    }

    public Data(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOlderContent() {
        return olderContent;
    }

    public void setOlderContent(String olderContent) {
        this.olderContent = olderContent;
    }

    public String getNewerContent() {
        return newerContent;
    }

    public void setNewerContent(String newerContent) {
        this.newerContent = newerContent;
    }

    public boolean hasMoreContent(int currentIndex) {
        return currentIndex < getOlderContent().length();
    }

    public boolean hasDifferenceAt(int charIndex) {
        return getOlderContent().charAt(charIndex) != getNewerContent().charAt(charIndex);
    }

    public boolean hasEqualLengths() {
        return getNewerContent().length() == getOlderContent().length();
    }

    public boolean hasNotEqualLengths() {
        return !hasEqualLengths();
    }

}
