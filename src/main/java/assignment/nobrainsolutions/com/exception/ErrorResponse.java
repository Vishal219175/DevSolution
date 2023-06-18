package assignment.nobrainsolutions.com.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


/**
 * The ErrorResponse class is used to represent the error response in
 * case of an exception or error occurring in the application
 */

@Setter
@Getter
public class ErrorResponse {

    /**
     * timestamp
     */

    private String timestamp;

    /**
     * status
     */

    private int status;

    /**
     * error
     */

    private String error;

    /**
     * message
     */

    private String message;

}