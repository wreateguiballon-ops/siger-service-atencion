package sat.siger.service.atencion.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> business(BusinessException ex, HttpServletRequest req) {
        return ResponseEntity.badRequest().body(new ErrorResponse(LocalDateTime.now(),400,"BUSINESS_ERROR",ex.getMessage(),req.getRequestURI()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> general(Exception ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(LocalDateTime.now(),500,"INTERNAL_ERROR",ex.getMessage(),req.getRequestURI()));
    }
}
