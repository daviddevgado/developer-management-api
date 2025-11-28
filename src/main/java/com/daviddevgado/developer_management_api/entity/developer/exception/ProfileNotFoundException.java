package com.daviddevgado.developer_management_api.entity.developer.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class ProfileNotFoundException extends BusinessException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
