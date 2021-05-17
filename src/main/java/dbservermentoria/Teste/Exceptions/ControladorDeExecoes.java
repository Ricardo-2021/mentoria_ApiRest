package dbservermentoria.Teste.Exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControladorDeExecoes extends ResponseEntityExceptionHandler {

   // private final MessageSource messageSource;

    @ExceptionHandler(IdNaoEncontradoNoBancoDeDadosException.class)
    public ResponseEntity<Object> handleAnyException(IdNaoEncontradoNoBancoDeDadosException e, WebRequest request) {
        String mensagem = "id não encontrado";
       // return handleExceptionInternal(e, mensagem, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>("teste", HttpStatus.CONFLICT);
    }

   @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)  {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return handleExceptionInternal(ex, errors, headers, status, request);
   }
}
