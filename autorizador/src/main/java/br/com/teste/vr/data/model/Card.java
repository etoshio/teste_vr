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

    @Column(name = "cardNumber", nullable = false)
    @NotBlank( message = "Cartão é obrigatório" )
    private String cardNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = new BigDecimal(500);
}
