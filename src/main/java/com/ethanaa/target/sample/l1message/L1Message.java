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
 * Make sure to update {@link EntityInfo} with any new {@link EntityId}s which you have added.
 *
 * @param <E> The canonical entity
 * @param <I> The entity's id enum
 * @param <T> The message type
 */
@JsonPropertyOrder({ "messageType", "entityInfo", "entity" })
public abstract class L1Message<E extends CanonicalEntity, I extends EntityId, T extends L1MessageType> {

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

    public L1Message(T messageType, EntityInfo<I> entityInfo, E entity) {

        this.messageType = messageType;
        this.entityInfo = entityInfo;
        this.entity = entity;
    }

    public L1Message(T messageType, EntityInfo<I> entityInfo) {
        this(messageType, entityInfo, null);
    }

    public L1Message() {

    }

    public T getMessageType() {
        return messageType;
    }

    protected void setMessageType(T messageType) {
        this.messageType = messageType;
    }

    public EntityInfo<I> getEntityInfo() {
        return entityInfo;
    }

    protected void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    @JsonIgnore
    public EntityType getEntityType() {
        return entityInfo.getEntityType();
    }

    @JsonIgnore
    public I getEntityIdProperty() {
        return entityInfo.getEntityIdProperty();
    }

    @JsonIgnore
    public String getEntityIdValue() {
        return entityInfo.getEntityIdValue();
    }

    public E getEntity() {
        return entity;
    }

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
