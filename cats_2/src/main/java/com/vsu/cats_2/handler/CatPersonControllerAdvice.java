package com.vsu.cats_2.handler;

import com.vsu.cats_2.exception.NotFoundException;
import com.vsu.cats_2.exception.RepositoryException;
import com.vsu.cats_2.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class CatPersonControllerAdvice {
    private final static Logger LOGGER =  Logger.getLogger(CatPersonControllerAdvice.class.getName());

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound(NotFoundException e){
        LOGGER.log(Level.WARNING,"not found exception"+e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidation(NotFoundException e){
        LOGGER.log(Level.WARNING,"validation exception"+e.getMessage());
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleRepositoryException(RepositoryException e){
        LOGGER.log(Level.WARNING,"repository exception" + e.getMessage());}
}
