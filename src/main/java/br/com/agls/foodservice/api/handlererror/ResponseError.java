package br.com.agls.foodservice.api.handlererror;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseError {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;

}
