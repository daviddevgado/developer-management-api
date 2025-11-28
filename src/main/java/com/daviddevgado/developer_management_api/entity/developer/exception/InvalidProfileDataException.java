package com.daviddevgado.developer_management_api.entity.developer.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class InvalidProfileDataException extends BusinessException {
    public InvalidProfileDataException(String message) {
        super(message);
    }
}
