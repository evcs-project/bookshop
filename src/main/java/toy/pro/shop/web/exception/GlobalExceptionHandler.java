package toy.pro.shop.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public  ResponseEntity<ErrorResponse> handleBindException(BindException ex)
    {
        return new ResponseEntity<>(new ErrorResponse(
                ErrorCode.PARAMETER_ERROR, HttpStatus.BAD_REQUEST.value(),  ex.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GlobalApiException.class)
    public ResponseEntity<ErrorResponse> handleGlobalApiException(GlobalApiException ex)
    {
        log.info("handleGlobalApiException");
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        log.error("error handler : handleMethodArgumentNotValidException");
        return new ResponseEntity<>(new ErrorResponse(
                ErrorCode.PARAMETER_ERROR, HttpStatus.BAD_REQUEST.value(),  e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse handleGlobalApiException(AccessDeniedException ex)
    {
        log.error("error handler : AccessDeniedException");
        return new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handException(Exception ex)
    {
        log.error("error handler : Exception");
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
