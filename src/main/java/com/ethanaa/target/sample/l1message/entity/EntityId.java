package com.ethanaa.target.sample.l1message.entity;

import com.ethanaa.target.sample.model.CanonicalEntity;

/**
 * Interface for {@code EntityId} enums.
 * <br/>
 * <br/>
 * Implementing enums define the properties which can identify an entity as well as binding themselves to an {@link EntityType}.
 * <br/>
 * <br/>
 * Note: Make sure to update {@link EntityInfo}'s {@code entityIdProperty} with any new {@link EntityId} implementations.
 */
public interface EntityId<E extends CanonicalEntity> {

    /**
     * Implement to create a binding between this {@link EntityId} and the {@link EntityType} which it identifies.
     *
     * @return {@link EntityType} - the entity type which this {@link EntityId} identifies
     */
    EntityType getEntityType();
}
