package com.ethanaa.target.sample.l1message;

import com.ethanaa.target.sample.l1message.entity.EntityId;
import com.ethanaa.target.sample.l1message.entity.EntityInfo;
import com.ethanaa.target.sample.l1message.entity.EntityType;
import com.ethanaa.target.sample.l1message.type.L1MessageType;
import com.ethanaa.target.sample.l1message.type.L1Notification;
import com.ethanaa.target.sample.l1message.type.L1RESTRequest;
import com.ethanaa.target.sample.model.CanonicalEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base class for messages passed into an L1 application where direct RESTful communication is undesirable.
 *<br/>
 * The key components of an L1Message are: {@link L1MessageType} and {@link EntityInfo}.
 *<br/><br/>
 * Note: Make sure to update {@link EntityInfo} with any new {@link EntityId}s which you have added.
 *
 * @param <E> {@link CanonicalEntity} - The canonical entity
 * @param <I> {@link EntityId} - The entity's id enum
 * @param <T> {@link L1MessageType} - The message type
 */
@JsonPropertyOrder({ "messageType", "entityInfo", "entity" })
public abstract class L1Message<E extends CanonicalEntity, I extends EntityId, T extends L1MessageType> {

    /**
     * Note: Make sure to update {@code @JsonSubTypes} with any new {@link L1MessageType}s which you have added.
     */
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
            @JsonSubTypes.Type(L1RESTRequest.class),
            @JsonSubTypes.Type(L1Notification.class)
    })
    private T messageType;

    private EntityInfo<I> entityInfo;

    private E entity;

    /**
     * Constructor for messages which have an attached entity.
     *
     * @param messageType {@link L1MessageType}
     * @param entityInfo {@link EntityInfo}
     * @param entity {@link CanonicalEntity}
     */
    public L1Message(T messageType, EntityInfo<I> entityInfo, E entity) {

        this.messageType = messageType;
        this.entityInfo = entityInfo;
        this.entity = entity;
    }

    /**
     * Constructor for messages.
     *
     * @param messageType {@link L1MessageType}
     * @param entityInfo {@link EntityInfo}
     */
    public L1Message(T messageType, EntityInfo<I> entityInfo) {
        this(messageType, entityInfo, null);
    }

    /**
     *  Default constructor for Jackson.
     */
    public L1Message() {

    }

    /**
     * Get the {@link L1MessageType}.
     *
     * @return {@link L1MessageType} - the message's type
     */
    public T getMessageType() {
        return messageType;
    }

    protected void setMessageType(T messageType) {
        this.messageType = messageType;
    }

    /**
     * Get the {@link EntityInfo}.
     *
     * @return {@link EntityInfo} - information about the entity which the message is targeting
     */
    public EntityInfo<I> getEntityInfo() {
        return entityInfo;
    }

    protected void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    /**
     * Get the {@link EntityType}.
     *
     * @return {@link EntityType} - the type of the entity
     */
    @JsonIgnore
    public EntityType getEntityType() {
        return entityInfo.getEntityType();
    }

    /**
     * Get the {@link Class} of the entity.
     *
     * @return {@link Class} - the class of the entity
     */
    @JsonIgnore
    public Class getEntityClass() {
        return getEntityType().getClazz();
    }

    /**
     * Get the {@link EntityId}.
     *
     * @return {@link EntityId} - the entity's id enum value
     */
    @JsonIgnore
    public I getEntityIdProperty() {
        return entityInfo.getEntityIdProperty();
    }

    /**
     * Get the id value.
     *
     * @return {@link String} - the entity's id value
     */
    @JsonIgnore
    public String getEntityIdValue() {
        return entityInfo.getEntityIdValue();
    }

    /**
     * Get the entity.
     *
     * @return {@link E}
     */
    public E getEntity() {
        return entity;
    }

    /**
     * Set the entity.
     *
     * @param entity {@link E} - the entity
     */
    public void setEntity(E entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "L1Message{" +
                "messageType=" + messageType +
                ", entityInfo=" + entityInfo +
                ", entity=" + entity +
                '}';
    }
}
