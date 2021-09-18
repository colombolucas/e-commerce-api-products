package br.senac.ecommerceapiprodutos.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorPayload {

    private LocalDateTime timestamp;
    private Integer Status;
    private String error;
    private String message;
    private String path;

}
