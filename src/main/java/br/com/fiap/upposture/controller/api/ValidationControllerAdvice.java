package br.com.fiap.upposture.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidationFieldErro> handle(MethodArgumentNotValidException e) {
        List<ValidationFieldErro> list = new ArrayList<>();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        errors.forEach(error -> {
            list.add(
                    new ValidationFieldErro(
                            error.getField(),
                            error.getDefaultMessage()
                    ));
        });
        return list;
    }

}