package org.poc.exceptions;

/**
 * Exception representing invalid Rectangle co-ordinates
 */
public class InvalidRectangleException extends Throwable {
    public InvalidRectangleException(){}

    public InvalidRectangleException(String message){
        super(message);
    }
}
