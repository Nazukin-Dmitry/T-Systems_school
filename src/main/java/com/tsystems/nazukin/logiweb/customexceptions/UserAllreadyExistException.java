package com.tsystems.nazukin.logiweb.customexceptions;

/**
 * Created by 1 on 11.02.2016.
 */
public class UserAllreadyExistException extends RuntimeException {
    public UserAllreadyExistException(String message) {
        super(message);
    }
}
