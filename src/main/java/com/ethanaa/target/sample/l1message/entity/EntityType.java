package com.ethanaa.target.sample.l1message.entity;


import com.ethanaa.target.sample.model.contactmech.ContactMechanism;
import com.ethanaa.target.sample.model.identity.Identity;

public enum EntityType {

    CONTACT_MECHANISM(ContactMechanism.class),
    IDENTITY(Identity.class);

    private String className;
    private Class clazz;

    EntityType(Class clazz) {

        this.clazz = clazz;
        this.className = clazz.getSimpleName();
    }

    public Class getClazz() {
        return clazz;
    }

    public static EntityType fromString(String string) {

        if (string != null) {
            for (EntityType entityType : EntityType.values()) {
                if (string.equalsIgnoreCase(entityType.toString())) {
                    return entityType;
                }
            }
        }

        throw new IllegalArgumentException(String.format("%s is not a valid EntityType.", string));
    }

    public String toString() {
        return this.className;
    }
}
