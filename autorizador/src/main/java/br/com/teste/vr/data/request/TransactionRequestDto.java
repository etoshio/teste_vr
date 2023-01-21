package br.com.teste.vr.data.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequestDto {

    @NotEmpty(message = "Número de cartão obrigatório")
    private String numeroCartao;
    @NotEmpty(message = "Senha obrigatório")
    private String senhaCartao;
    @NotEmpty(message = "Valor obrigatório")
    private BigDecimal valor;
}
