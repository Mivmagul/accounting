package com.mivmagul.stakelogic.accounting.application.exception;

import com.mivmagul.stakelogic.accounting.application.domain.BaseEntity;

public class EntityFieldValidationException extends RuntimeException {
    private static final String EMPTY_FIELD_ERROR_MESSAGE = "Mandatory field should not be empty";

    private final BaseEntity entity;

    public EntityFieldValidationException(BaseEntity entity) {
        super();
        this.entity = entity;
    }

    public String getEntity() {
        return entity.toString();
    }

    @Override
    public String getMessage() {
        return EMPTY_FIELD_ERROR_MESSAGE;
    }
}
