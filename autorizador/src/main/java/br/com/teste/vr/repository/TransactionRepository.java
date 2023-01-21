package br.com.teste.vr.repository;

import br.com.teste.vr.data.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long > {
}
