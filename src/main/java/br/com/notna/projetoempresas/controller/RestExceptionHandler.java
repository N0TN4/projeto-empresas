package br.com.notna.projetoempresas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.notna.projetoempresas.model.ErrorResponse;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);

    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
            List<ObjectError> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(), status.getReasonPhrase(),
                ex.getBindingResult().getObjectName(), errors);
    }

    private List<ObjectError> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ObjectError(error.getDefaultMessage(), error.getField()))
                .collect(Collectors.toList());
    }

}
