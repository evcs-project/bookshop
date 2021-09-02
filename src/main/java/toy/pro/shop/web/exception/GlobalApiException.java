package toy.pro.shop.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalApiException extends RuntimeException{

    private final String message;

    public GlobalApiException(ErrorCode errorCode)
    {
        this.message = errorCode.getName();
    }

    public GlobalApiException(final String message)
    {
        this.message = message;
    }
}