package pdev.com.agenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BusinessException> handleBusinessException(){
        BusinessException e = new BusinessException("CPF já existe");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
    }
}
