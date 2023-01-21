package br.com.teste.vr.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", unique = true, nullable = false )
    private Long id;

    @Column(name = "cardNumber", nullable = false)
    @NotBlank( message = "Cartão é obrigatório" )
    private String cardNumber;

    @Column(name = "value", nullable = false)
    private BigDecimal value = new BigDecimal(500);

    @CreationTimestamp
    private LocalDateTime createDateTime;
}
