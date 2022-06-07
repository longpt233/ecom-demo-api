package com.company.team.exception;

import com.company.team.exception.custom.DuplicateRecordException;
import com.company.team.exception.custom.InternalServerException;
import com.company.team.exception.custom.NotFoundException;
import com.company.team.exception.custom.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * tự động trả về lỗi nếu controller cuối cùng vẫn throw ra 1/ những lỗi sau
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> handlerDuplicateException(DuplicateRecordException ex, WebRequest request) {
        //Log err
        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException ex, WebRequest request) {
        //Log err
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<?> handlerInternalServerException(InternalServerException ex, WebRequest request) {
        //Log err
        ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerUnknownException(Exception ex, WebRequest request) {
        //Log err
        ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "unknown exception");
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = TokenRefreshException.class)
    public ResponseEntity<?> handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {

        ErrorResponse err = new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

}
