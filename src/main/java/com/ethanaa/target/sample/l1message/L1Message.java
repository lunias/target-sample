package com.ethanaa.target.sample.l1message;

import com.ethanaa.target.sample.l1message.entity.EntityId;
import com.ethanaa.target.sample.l1message.entity.EntityInfo;
import com.ethanaa.target.sample.l1message.entity.EntityType;
import com.ethanaa.target.sample.l1message.type.L1MessageType;
import com.ethanaa.target.sample.l1message.type.L1Notification;
import com.ethanaa.target.sample.l1message.type.L1RESTRequest;
import com.ethanaa.target.sample.model.CanonicalEntity;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.*;

/**
 * Base class for messages passed into an L1 application where direct RESTful communication
 * is undesirable.
 *<br/>
 * The key components of an L1Message are: {@link L1MessageType} and {@link EntityInfo}.
 * <br/><br/>
 * This class provides a built-in {@link ObjectMapper} for converting to and from json.
 * It is recommended to use the methods {@link #toJson() toJson()} and
 * {@link #fromJson(String, Class)} for serialization / deserialization to maintain
 * consistency during integration.
 * <br/><br/>
 * Note: Make sure to update {@link EntityInfo} with any new {@link EntityId}s which you
 * have added.
 *
 * @param <E> {@link CanonicalEntity} - The canonical entity
 * @param <I> {@link EntityId} - The entity's id enum
 * @param <V> {@link Object} - The entity's id value type
 * @param <T> {@link L1MessageType} - The message type
 */
@JsonPropertyOrder({ "messageType", "entityInfo", "entity" })
public abstract class L1Message
        <E extends CanonicalEntity, I extends EntityId<E>, V, T extends L1MessageType> {

    private static final ObjectMapper MAPPER;

    static {

        MAPPER = new ObjectMapper();

        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Note: Make sure to update {@code @JsonSubTypes} with any new {@link L1MessageType}s
     * which you have added.
     */
    @JsonTypeInfo(
            use = Id.NAME,
            include = As.WRAPPER_OBJECT)
    @JsonSubTypes({
            @JsonSubTypes.Type(L1RESTRequest.class),
            @JsonSubTypes.Type(L1Notification.class)
    })
    private T messageType;

    private EntityInfo<I, V> entityInfo;

    private E entity;

    /**
     * Constructor for messages which have an attached entity.
     *
     * @param messageType {@link L1MessageType}
     * @param entityInfo {@link EntityInfo}
     * @param entity {@link CanonicalEntity}
     */
    public L1Message(T messageType, EntityInfo<I, V> entityInfo, E entity) {

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
    public L1Message(T messageType, EntityInfo<I, V> entityInfo) {
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
     * @return {@link EntityInfo} - information about the entity which the message is
     * targeting
     */
    public EntityInfo<I, V> getEntityInfo() {
        return entityInfo;
    }

    protected void setEntityInfo(EntityInfo<I, V> entityInfo) {
        this.entityInfo = entityInfo;
    }

    /**
     * Get the {@link EntityType}.
     *
     * @return {@link EntityType} - the type of the entity
     */
    @JsonIgnore
    public final EntityType getEntityType() {
        return entityInfo.getEntityType();
    }

    /**
     * Get the {@link Class} of the entity.
     *
     * @return {@link Class} - the class of the entity
     */
    @JsonIgnore
    public final Class getEntityClass() {
        return getEntityType().getClazz();
    }

    /**
     * Get the {@link EntityId}.
     *
     * @return {@link EntityId} - the entity's id enum value
     */
    @JsonIgnore
    public final I getEntityIdProperty() {
        return entityInfo.getEntityIdProperty();
    }

    /**
     * Get the id value.
     *
     * @return {@link Object} - the entity's id value
     */
    @JsonIgnore
    public final V getEntityIdValue() {
        return entityInfo.getEntityIdValue();
    }

    /**
     * Get the entity.
     *
     * @return {@link E}
     */
    public final E getEntity() {
        return entity;
    }

    /**
     * Set the entity.
     *
     * @param entity {@link E} - the entity
     */
    public final void setEntity(E entity) {
        this.entity = entity;
    }

    /**
     * Get the json representation of this message.
     * <br/>
     * <br/>
     * Uses the internal static singleton {@link ObjectMapper} to create {@link ObjectWriter}s
     * for processing in a thread safe and performant manner.
     *
     * @return {@link String} - json representation of the message
     * @throws JsonProcessingException when processing failed
     */
    public String toJson()
            throws JsonProcessingException {

        ObjectWriter writer = MAPPER.writerWithDefaultPrettyPrinter();

        return writer.writeValueAsString(this);
    }

    /**
     * Convert an implementation of {@link L1Message} to its json representation.
     * <br/>
     * <br/>
     * Uses the internal static singleton {@link ObjectMapper} to create {@link ObjectReader}s
     * for processing in a thread safe and performant manner.
     *
     * @param json {@link String} - json representation of the message
     * @param l1MessageClass {@link Class} - class of the {@link L1Message} implementation
     * @param <T> {@link L1MessageType} - implementation of {@link L1MessageType}
     * @return {@link L1Message} - the {@link L1Message} read from the provided json
     * @throws IOException when processing failed
     */
    public static <T extends L1Message> T fromJson(String json, Class<T> l1MessageClass)
            throws IOException {

        ObjectReader reader = MAPPER.readerFor(l1MessageClass);

        return reader.readValue(json);
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
