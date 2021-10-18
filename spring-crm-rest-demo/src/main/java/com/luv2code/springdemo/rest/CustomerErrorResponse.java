package com.luv2code.springdemo.rest;

public class CustomerErrorResponse {
    private int status;
    private String message;
    private String dateTime;

    public CustomerErrorResponse() {
    }

    public CustomerErrorResponse(int status, String message, String dateTime) {
        this.status = status;
        this.message = message;
        this.dateTime = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
