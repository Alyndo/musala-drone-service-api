package com.droneservice.droneserviceapi.utils.response;

import com.droneservice.droneserviceapi.utils.dto.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class CommonResponse {

  private int statusCode;
  private boolean success;
  private String message;
  private List<Error> errors;

    @Override
    public String toString() {
        return "statusCode=" + statusCode +
                ", success=" + success +
                ", message='" + message + '\'';
    }
}
