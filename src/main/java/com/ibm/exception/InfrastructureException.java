package com.ibm.exception;

/**
 * Created by peo_rboliveira on 05/04/16.
 */
public class InfrastructureException extends RuntimeException {

    public InfrastructureException(String msg) {
        super(msg);
    }

    public InfrastructureException(String msg, Throwable tw) {
        super(msg, tw);
    }
}
