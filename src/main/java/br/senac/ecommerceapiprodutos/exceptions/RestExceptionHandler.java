package br.senac.ecommerceapiprodutos.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorPayload handleValidateException(MethodArgumentNotValidException exception, HttpServletRequest request){
        BindingResult result = exception.getBindingResult();
        return ErrorPayload.builder()
                .timestamp(LocalDateTime.now())
                .Status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .message(result.getFieldErrors().get(0).getDefaultMessage())
                .path(request.getContextPath() + request.getServletPath())
                .build();

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorPayload handleNotFoundException(Exception exception, HttpServletRequest request){
        return ErrorPayload.builder()
                .timestamp(LocalDateTime.now())
                .Status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getContextPath() + request.getServletPath())
                .build();

    }


}
