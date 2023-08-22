package pt.italo.labseq.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pt.italo.labseq.exception.ValueUnderZeroException;

@RestControllerAdvice
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {ValueUnderZeroException.class})
    public ErrorBean valueUnderZeroException(ValueUnderZeroException valueUnderZeroException){
        return ErrorBean.builder().error(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(valueUnderZeroException.getMessage()).build();
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ErrorBean exception(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        return ErrorBean.builder().error(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("Can't convert letters in numbers, please send a number when you try again.").build();
    }

}
