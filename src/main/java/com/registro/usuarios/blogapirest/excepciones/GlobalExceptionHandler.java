package com.registro.usuarios.blogapirest.excepciones;

import com.registro.usuarios.blogapirest.dto.ErrorDetalles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/*MANEJO DE EXCEPCIONES EN SPRING BOOT*/
@ControllerAdvice //Manejará todas las capturas de excepciones de toda nuestra aplicación
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //maneja las excepciones que se le detallen a la clase que le hemos pasado.
    private ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogAppException.class) //maneja las excepciones que se le detallen a la clase que le hemos pasado.
    private ResponseEntity<ErrorDetalles> manejarBlogAppException(BlogAppException exception, WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class) //maneja las excepciones que se le detallen a la clase que le hemos pasado.
    private ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //esto se usará cuando los atributos de los objetos que les pasemos no sean válidos

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(nombreCampo, mensaje);
        });

        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }

}
