package com.daviddevgado.developer_management_api.entity.technology.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class TechnologyNotFoundException extends BusinessException {
    public TechnologyNotFoundException(String message) {
        super(message);
    }
}
