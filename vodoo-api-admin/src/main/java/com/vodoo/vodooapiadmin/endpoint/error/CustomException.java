package com.vodoo.vodooapiadmin.endpoint.error;

/**
 * Created by ahmed on 19/07/2019.
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }


}
