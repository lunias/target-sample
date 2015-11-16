package com.ethanaa.target.sample.l1message.entity;


import com.ethanaa.target.sample.model.CanonicalEntity;
import com.ethanaa.target.sample.model.contactmech.ContactMechanism;
import com.ethanaa.target.sample.model.identity.Identity;

/**
 * Enum which defines the types of entities as well as providing access to the entity's class.
 */
public enum EntityType {

    CONTACT_MECHANISM(ContactMechanism.class, Constants.CONTACT_MECHANISM_VALUE),
    IDENTITY(Identity.class, Constants.IDENTITY_VALUE);

    public static class Constants {
        public static final String CONTACT_MECHANISM_VALUE = "ContactMechanism";
        public static final String IDENTITY_VALUE = "Identity";
    }

    private String simpleName;
    private Class<? extends CanonicalEntity> clazz;

    EntityType(Class<? extends CanonicalEntity> clazz, String simpleName) {

        this.clazz = clazz;
        this.simpleName = simpleName;
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
     * @param simpleName {@link String} - the simple name of the entity class
     * @return {@link EntityType} - the enum value representing the entity
     */
    public static EntityType fromString(String simpleName) {

        if (simpleName != null) {
            for (EntityType entityType : EntityType.values()) {
                if (simpleName.equalsIgnoreCase(entityType.toString())) {
                    return entityType;
                }
            }
        }

        throw new IllegalArgumentException(String.format("%s is not a valid EntityType.", simpleName));
    }


    /**
     * Returns the simple name of the entity class.
     *
     * @return {@link String} - the simple name of the entity class
     */
    @Override
    public String toString() {
        return this.simpleName;
    }
}
