package com.daviddevgado.developer_management_api.entity.developer.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class InvalidDeveloperDataException extends BusinessException {
    public InvalidDeveloperDataException(String message) {
        super(message);
    }
}

