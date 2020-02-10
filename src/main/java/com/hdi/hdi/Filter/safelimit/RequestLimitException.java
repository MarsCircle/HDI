package com.hdi.hdi.Filter.safelimit;


public class RequestLimitException extends Exception {

    private static final long serialVersionUID = 1555967171104727461L;

    public RequestLimitException() {
        super("该时段请求次数过多，超过限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}


