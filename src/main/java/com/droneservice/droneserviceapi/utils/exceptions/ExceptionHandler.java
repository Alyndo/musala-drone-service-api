package com.droneservice.droneserviceapi.utils.exceptions;

import com.droneservice.droneserviceapi.utils.dto.Error;
import com.droneservice.droneserviceapi.utils.enums.MessageCode;
import com.droneservice.droneserviceapi.utils.message.MessageService;
import com.droneservice.droneserviceapi.utils.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static com.droneservice.droneserviceapi.utils.dto.Constants.FAILURE_INT_VALUE;
import static com.droneservice.droneserviceapi.utils.dto.Constants.INVALID_PARAM;

@ControllerAdvice
public class ExceptionHandler {

    private MessageService messageService;

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleApiValidations(MethodArgumentNotValidException ex, WebRequest request){
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String msg = String.format("%s : %s",fieldError.getField(),fieldError.getDefaultMessage());
            errors.add(Error.builder().code(INVALID_PARAM).message(msg).build());
        });
        String message = messageService.getMessage(MessageCode.MESSAGE_INVALID_REQUEST_SUPPLIED.getCode(),
                new String[]{}, request.getLocale());

        CommonResponse response = CommonResponse.builder()
                .errors(errors)
                .success(Boolean.FALSE)
                .message(message)
                .statusCode(FAILURE_INT_VALUE).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
