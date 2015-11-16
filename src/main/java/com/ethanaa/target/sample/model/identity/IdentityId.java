package com.ethanaa.target.sample.model.identity;


import com.ethanaa.target.sample.l1message.entity.EntityId;
import com.ethanaa.target.sample.l1message.entity.EntityType;

/**
 * Enum which defines the properties which identify an {@link Identity}.
 */
public enum IdentityId implements EntityId<Identity> {

    TARGET_GUID, IDENTITY_ID;

    @Override
    public EntityType getEntityType() {
        return EntityType.IDENTITY;
    }
}
