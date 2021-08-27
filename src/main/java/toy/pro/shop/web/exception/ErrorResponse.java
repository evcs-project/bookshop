package toy.pro.shop.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception 발생 시 응답하는 에러 정보
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String message;
    private Integer statusCode;
    private List<FieldError> errors;

    public ErrorResponse(String message)
    {

        this.message = message;
    }

    public ErrorResponse(String message, int statusCode)
    {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErrorResponse(ErrorCode errorCode, int statusCode, BindingResult bindingResult)
    {
        this.message = errorCode.getName();
        this.statusCode = statusCode;
        this.errors = bindingResult.getFieldErrors().stream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .field(error.getField())
                        .reason(error.getDefaultMessage())
                        .value(ObjectUtils.isEmpty(error.getRejectedValue()) ? "" : error.getRejectedValue().toString())
                        .build()
                ).collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }
}
