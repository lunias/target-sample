package com.ethanaa.target.sample.model.identity;


import com.ethanaa.target.sample.model.CanonicalEntity;

public class Identity implements CanonicalEntity {

    private String targetGuid;

    public Identity(String targetGuid) {
        this.targetGuid = targetGuid;
    }

    public Identity() {

    }

    public String getTargetGuid() {
        return targetGuid;
    }

    public void setTargetGuid(String targetGuid) {
        this.targetGuid = targetGuid;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "targetGuid='" + targetGuid + '\'' +
                '}';
    }
}
