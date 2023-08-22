package pt.italo.labseq.exception;

import java.util.Objects;

public class ValueUnderZeroException extends RuntimeException {

    private static String DEFAULT_ERROR_MESSAGE = "The value of N can't be less then zero.";

    public ValueUnderZeroException(String error){
        super(createMessageError(error));
    }

    private static String createMessageError(String error) {

        if(Objects.isNull(error) || error.isEmpty()) {
            return DEFAULT_ERROR_MESSAGE;
        } else {
            return error;
        }
    }

}