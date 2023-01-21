package br.com.teste.vr.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", unique = true, nullable = false )
    private Long id;

    @Column(name = "numeroCartao", nullable = false)
    @NotBlank( message = "Cartão é obrigatório" )
    private String numeroCartao;

    @Column(name = "senha")
    private String senha;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo = new BigDecimal(500);
}
