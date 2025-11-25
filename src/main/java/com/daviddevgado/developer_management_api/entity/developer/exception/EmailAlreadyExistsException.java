package com.daviddevgado.developer_management_api.entity.developer.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
