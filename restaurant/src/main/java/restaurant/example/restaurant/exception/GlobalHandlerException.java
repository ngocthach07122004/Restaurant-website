package restaurant.example.restaurant.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import restaurant.example.restaurant.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handlerAppException (AppException appException ){
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(appException.getErrorCode().getCode())
                .message(appException.getErrorCode().getMessage())
                .build();
         return ResponseEntity.badRequest().body(apiResponse);
    }

}
