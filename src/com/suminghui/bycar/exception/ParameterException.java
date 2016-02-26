package com.suminghui.bycar.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception {

    private static final long serialVersionUID = -7281673555231045006L;

    private Map<String, String> errorFields = new HashMap<String, String>();

    private String code;

    private String message;

    public ParameterException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ParameterException() {
        // nothing to do
    }

    public ParameterException(String code) {
        super();
        this.code = code;
    }

    public Map<String, String> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(Map<String, String> errorFields) {
        this.errorFields = errorFields;
    }

    public void addErrorField(String fieldName, String message) {
        errorFields.put(fieldName, message);
    }

    public boolean isErrorField() {
        return !errorFields.isEmpty();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
