package toy.pro.shop.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalApiException.class)
    public ResponseEntity<ErrorResponse> handleGlobalApiException(GlobalApiException ex)
    {
        log.info("handleGlobalApiException");
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex)
//    {
//        log.info("error handler : BadCredentialsException handler");
//        return new ResponseEntity<>(
//                new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        log.error("error handler : handleMethodArgumentNotValidException");
        return new ResponseEntity<>(new ErrorResponse(
                ErrorCode.INVALID_DATA, HttpStatus.BAD_REQUEST.value(),  e.getBindingResult()), HttpStatus.BAD_REQUEST);
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
