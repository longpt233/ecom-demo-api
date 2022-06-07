package com.company.team.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {

    ErrorResponse err = new ErrorResponse(HttpStatus.EXPECTATION_FAILED, "File too large!");
    return new ResponseEntity<>(err, HttpStatus.EXPECTATION_FAILED);
  }
}