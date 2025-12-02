package com.daviddevgado.developer_management_api.entity.technology.exception;

import com.daviddevgado.developer_management_api.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class TechnologyInUseException extends BusinessException {
    public TechnologyInUseException(String techName, int devCount, int projectCount) {
        super(
                String.format("Technology '%s' cannot be deleted - used by %d developers and %d projects.",
                        techName, devCount, projectCount),
                "TECHNOLOGY_IN_USE",
                HttpStatus.CONFLICT
        );
    }
}

