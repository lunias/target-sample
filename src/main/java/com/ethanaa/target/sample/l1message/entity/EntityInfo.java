package com.ethanaa.target.sample.l1message.entity;


import com.ethanaa.target.sample.model.contactmech.ContactMechanismId;
import com.ethanaa.target.sample.model.identity.IdentityId;
import com.fasterxml.jackson.annotation.*;

@JsonPropertyOrder({ "entityType", "entityIdProperty", "entityIdValue" })
public class EntityInfo<I extends EntityId> {

    private EntityType entityType;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "entityType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = ContactMechanismId.class, name = "ContactMechanism"),
            @JsonSubTypes.Type(value = IdentityId.class, name = "Identity")
    })
    private I entityIdProperty;

    private String entityIdValue;

    public EntityInfo(I entityIdProperty, String entityIdValue) {

        if (entityIdProperty == null) {
            throw new IllegalArgumentException("entityIdProperty cannot be null");
        }

        this.entityType = entityIdProperty.getEntityType();
        this.entityIdProperty = entityIdProperty;
        this.entityIdValue = entityIdValue;
    }

    public EntityInfo() {

    }

    public String getTypeString() {
        return entityType.toString();
    }

    @JsonProperty("entityType")
    protected void setTypeString(String entityType) {
        this.entityType = EntityType.fromString(entityType);
    }

    public EntityType getEntityType() {
        return entityType;
    }

    @JsonIgnore
    protected void setEntityType(EntityType type) {
        this.entityType = type;
    }

    public I getEntityIdProperty() {
        return entityIdProperty;
    }

    public void setEntityIdProperty(I entityIdProperty) {
        this.entityIdProperty = entityIdProperty;
    }

    public String getEntityIdValue() {
        return entityIdValue;
    }

    public void setEntityIdValue(String entityIdValue) {
        this.entityIdValue = entityIdValue;
    }

    @Override
    public String toString() {
        return "EntityInfo{" +
                "entityType=" + entityType +
                ", entityIdProperty=" + entityIdProperty +
                ", entityIdValue='" + entityIdValue + '\'' +
                '}';
    }
}
