package com.mivmagul.stakelogic.accounting.application.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final String ENTITY_NOT_FOUND_ERROR_MESSAGE = "Entity not found";

    private final Object entity;

    public EntityNotFoundException(Object entity) {
        super();
        this.entity = entity;
    }

    public String getEntity() {
        return String.valueOf(entity);
    }

    @Override
    public String getMessage() {
        return ENTITY_NOT_FOUND_ERROR_MESSAGE;
    }
}
