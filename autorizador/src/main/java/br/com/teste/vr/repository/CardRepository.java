package br.com.teste.vr.repository;

import br.com.teste.vr.data.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Long > {
    Optional<Card> findByNumeroCartao(String cardNumber);
}
