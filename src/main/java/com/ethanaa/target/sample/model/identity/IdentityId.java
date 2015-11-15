package com.ethanaa.target.sample.model.identity;


import com.ethanaa.target.sample.l1message.entity.EntityId;
import com.ethanaa.target.sample.l1message.entity.EntityType;

public enum IdentityId implements EntityId {

    TARGET_GUID, IDENTITY_ID;

    @Override
    public EntityType getEntityType() {
        return EntityType.IDENTITY;
    }
}