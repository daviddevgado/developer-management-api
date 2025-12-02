package com.daviddevgado.developer_management_api.entity.technology.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class TechnologyAlreadyExistsException extends BusinessException {
    public TechnologyAlreadyExistsException(String message) {
        super(message);
    }
}
