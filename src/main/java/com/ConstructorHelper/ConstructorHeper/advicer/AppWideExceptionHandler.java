package com.ConstructorHelper.ConstructorHeper.advicer;

import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.exception.NotFoundedException;
import com.ConstructorHelper.ConstructorHeper.util.StandedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {



    @ExceptionHandler(NotFoundedException.class)
    public ResponseEntity<StandedResponse> handleNotFoundException(NotFoundedException e)
    {
        return new ResponseEntity<StandedResponse>(
                new StandedResponse(404,"error",e.getMessage()), HttpStatus.NOT_FOUND
            );

    }


    @ExceptionHandler(AlreadyReportedException.class)
    public ResponseEntity<StandedResponse> handleAlreadyReportedException(AlreadyReportedException e)
    {
        return new ResponseEntity<StandedResponse>(
                new StandedResponse(208,"error",e.getMessage()),HttpStatus.ALREADY_REPORTED
        );
    }



}
