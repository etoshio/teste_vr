package br.com.teste.vr.data.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransactionRequestDto {

    @NotEmpty(message = "Número de cartão obrigatório")
    private String cardNumber;
    @NotEmpty(message = "Senha obrigatório")
    private String password;
    @NotEmpty(message = "Valor obrigatório")
    private BigDecimal value;
}
