package com.daviddevgado.developer_management_api.entity.developer.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class DeveloperNotFoundException extends BusinessException {
    public DeveloperNotFoundException(String message) {
        super(message);
    }
}
