package com.ethanaa.target.sample.model.contactmech;

import com.ethanaa.target.sample.l1message.entity.EntityId;
import com.ethanaa.target.sample.l1message.entity.EntityType;

/**
 * Enum which defines the properties which identify a {@link ContactMechanism}.
 */
public enum ContactMechanismId implements EntityId<ContactMechanism> {

    TARGET_GUID, CONTACT_MECHANISM_ID, CONTACT_MECHANISM_VALUE;

    @Override
    public EntityType getEntityType() {
        return EntityType.CONTACT_MECHANISM;
    }
}
