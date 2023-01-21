package br.com.teste.vr.data.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CardRequestDto {
    @NotEmpty(message = "Número de cartão obrigatório")
    private String numeroCartao;
    @NotEmpty(message = "Senha obrigatório")
    private String senha;
}
