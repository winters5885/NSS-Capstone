package com.nashss.se.ascendnashville.Exceptions;

/**
 * Exception to throw when a given CalendarId is not found
 * in the database.
 */
public class CalendarNotFoundException extends RuntimeException {

    /**
     * Exception with no message or cause.
     */
    public CalendarNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CalendarNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CalendarNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public CalendarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
