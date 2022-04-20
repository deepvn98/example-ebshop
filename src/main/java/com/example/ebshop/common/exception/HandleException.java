package com.example.ebshop.common.exception;

public class HandleException extends Exception {
    private String message;
    private String code;

    public HandleException() {
    }

    public HandleException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
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
