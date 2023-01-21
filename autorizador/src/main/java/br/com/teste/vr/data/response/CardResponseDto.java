package br.com.teste.vr.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {

    private String numeroCartao;
    private String senha;
    private BigDecimal saldo;
}
