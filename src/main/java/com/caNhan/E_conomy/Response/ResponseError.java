package com.caNhan.E_conomy.Response;

public class ResponseError {
    private int statusCode;
    private String message;

    public ResponseError(int statusCode, String message)
    {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
