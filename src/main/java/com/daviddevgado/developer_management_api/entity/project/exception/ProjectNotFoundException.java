package com.daviddevgado.developer_management_api.entity.project.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class ProjectNotFoundException extends BusinessException {
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
