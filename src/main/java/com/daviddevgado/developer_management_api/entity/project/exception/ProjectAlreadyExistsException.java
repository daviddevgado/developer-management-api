package com.daviddevgado.developer_management_api.entity.project.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class ProjectAlreadyExistsException extends BusinessException {
    public ProjectAlreadyExistsException(String message) {
        super(message);
    }
}
