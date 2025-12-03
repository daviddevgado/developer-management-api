package com.daviddevgado.developer_management_api.entity.project.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;

public class ProjectDataInvalidException extends BusinessException {
    public ProjectDataInvalidException(String message) {
        super(message);
    }
}
