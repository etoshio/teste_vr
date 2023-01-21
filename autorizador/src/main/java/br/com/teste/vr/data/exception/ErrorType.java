package br.com.teste.vr.data.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

@Schema
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ErrorType {
    public static final ErrorType DUPLICATED_CARD = new ErrorType(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já cadastrado. Tente novamente com outro número.");
    public static final ErrorType NOT_FOUND = new ErrorType(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
    public static final ErrorType PASSWORD_INVALID = new ErrorType(HttpStatus.BAD_REQUEST, "Senha inválido.");
    public static final ErrorType INSUFFICIENT_BALANCE = new ErrorType(HttpStatus.BAD_REQUEST, "Saldo insuficiente.");


    @NonNull
    @Schema(name = "status code", example = "400", required = true)
    private HttpStatus status;

    @NonNull
    @Schema(name = "Error message", example = "Error message", required = true)
    private String message;

    @Schema(name = "Error details", example = "Error details")
    private String details;

}