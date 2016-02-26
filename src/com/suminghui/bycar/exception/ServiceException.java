package com.suminghui.bycar.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 8840217978021380863L;

    private String code;
    private String message;

    public ServiceException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ServiceException(String code) {
        super();
        this.code = code + "";
    }
    
    public ServiceException() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }



}
