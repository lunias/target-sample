package com.ethanaa.target.sample.l1message.entity;


import com.ethanaa.target.sample.model.CanonicalEntity;
import com.ethanaa.target.sample.model.contactmech.ContactMechanism;
import com.ethanaa.target.sample.model.identity.Identity;

/**
 * Enum which defines the types of entities as well as providing access to the entity's class.
 */
public enum EntityType {

    CONTACT_MECHANISM(ContactMechanism.class),
    IDENTITY(Identity.class);

    private String className;
    private Class<? extends CanonicalEntity> clazz;

    EntityType(Class<? extends CanonicalEntity> clazz) {

        this.clazz = clazz;
        this.className = clazz.getSimpleName();
    }

    /**
     * Get the {@link Class} of the entity.
     *
     * @return {@link Class} - the class of the entity
     */
    public Class<? extends CanonicalEntity> getClazz() {
        return clazz;
    }

    /**
     * Parse the provided simple class name of the entity and return the corresponding {@link EntityType}.
     *
     * @param className {@link String} - the simple name of the entity class
     * @return {@link EntityType} - the enum value representing the entity
     */
    public static EntityType fromString(String className) {

        if (className != null) {
            for (EntityType entityType : EntityType.values()) {
                if (className.equalsIgnoreCase(entityType.toString())) {
                    return entityType;
                }
            }
        }

        throw new IllegalArgumentException(String.format("%s is not a valid EntityType.", className));
    }


    /**
     * Returns the simple name of the entity class.
     *
     * @return {@link String} - the simple name of the entity class
     */
    @Override
    public String toString() {
        return this.className;
    }
}
