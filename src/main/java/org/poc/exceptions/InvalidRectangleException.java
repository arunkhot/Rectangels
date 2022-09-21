package org.poc.exceptions;

public class InvalidRectangleException extends Throwable {

    public InvalidRectangleException(){}

    public InvalidRectangleException(String message){
        super(message);
    }
}
