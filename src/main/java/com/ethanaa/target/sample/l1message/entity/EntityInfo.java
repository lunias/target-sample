package com.ethanaa.target.sample.l1message.entity;


import com.ethanaa.target.sample.model.CanonicalEntity;
import com.ethanaa.target.sample.model.contactmech.ContactMechanismId;
import com.ethanaa.target.sample.model.identity.IdentityId;
import com.fasterxml.jackson.annotation.*;

/**
 * Information on an entity's type, the property which identifies it, and the value of that identifying property.
 * <br/>
 * <br/>
 * The {@link EntityType} is derived from the provided {@link EntityId} to prevent mismatched identifiers.
 *
 * @param <I> {@link EntityId} - the entity's id enum
 */
@JsonPropertyOrder({ "entityType", "entityClass", "entityIdProperty", "entityIdValue" })
public class EntityInfo<I extends EntityId> {

    private EntityType entityType;

    /**
     * Note: Make sure to update {@code @JsonSubTypes} with any new {@link EntityId}s which you have added.
     */
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

    /**
     * Constructor for the information necessary to identify the entity.
     *
     * @param entityIdProperty {@link EntityId} - the entity's identifying property
     * @param entityIdValue {@link String} - the entity's id value
     */
    public EntityInfo(I entityIdProperty, String entityIdValue) {

        if (entityIdProperty == null) {
            throw new IllegalArgumentException("entityIdProperty cannot be null");
        }

        if (entityIdValue == null) {
            throw new IllegalArgumentException("entityIdValue cannot be null");
        }

        this.entityType = entityIdProperty.getEntityType();
        this.entityIdProperty = entityIdProperty;
        this.entityIdValue = entityIdValue;
    }

    /**
     *  Default constructor for Jackson.
     */
    public EntityInfo() {

    }

    /**
     * Get the {@link EntityType} in {@code String} format.
     *
     * @return {@link String} - the simple class name of the entity's type
     */
    public String getTypeString() {
        return entityType.toString();
    }

    @JsonProperty("entityType")
    protected void setTypeString(String entityType) {
        this.entityType = EntityType.fromString(entityType);
    }

    /**
     * Get the {@link EntityType}.
     *
     * @return {@link EntityType} - the entity's type
     */
    public EntityType getEntityType() {
        return entityType;
    }

    @JsonIgnore
    protected void setEntityType(EntityType type) {
        this.entityType = type;
    }

    /**
     * Get the {@link Class} of the {@link EntityType}.
     *
     * @return {@link Class} - the entity's class
     */
    public Class<? extends CanonicalEntity> getEntityClass() {
        return entityType.getClazz();
    }

    /**
     * Get the {@link EntityId}.
     *
     * @return {@link EntityId} - the entity's id enum value
     */
    public I getEntityIdProperty() {
        return entityIdProperty;
    }

    /**
     * Set the {@link EntityId}.
     *
     * @param entityIdProperty {@link EntityId} - the entity's id enum value
     */
    public void setEntityIdProperty(I entityIdProperty) {
        this.entityIdProperty = entityIdProperty;
    }

    /**
     * Get the id value.
     *
     * @return {@link String} - the entity's id value
     */
    public String getEntityIdValue() {
        return entityIdValue;
    }

    /**
     * Set the id value.
     *
     * @param entityIdValue {@link String} - the entity's id value
     */
    public void setEntityIdValue(String entityIdValue) {
        this.entityIdValue = entityIdValue;
    }

    @Override
    public String toString() {
        return "EntityInfo{" +
                "entityType=" + entityType +
                ", entityClass=" + getEntityClass() +
                ", entityIdProperty=" + entityIdProperty +
                ", entityIdValue='" + entityIdValue + '\'' +
                '}';
    }
}
