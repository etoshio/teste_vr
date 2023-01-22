package br.com.teste.vr.data.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CardRequestDto {
    @NotEmpty(message = "Número de cartão obrigatório")
    private String numeroCartao;
    @NotEmpty(message = "Senha obrigatório")
    private String senha;
}
