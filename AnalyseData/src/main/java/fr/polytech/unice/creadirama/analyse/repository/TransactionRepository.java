package fr.polytech.unice.creadirama.analyse.repository;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
