package com.daviddevgado.developer_management_api.entity.technology.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class TechnologyDataInvalidException extends BusinessException {
    public TechnologyDataInvalidException(String message) {
        super(message);
    }
}
