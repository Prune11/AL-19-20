package fr.polytech.unice.credirama.transaction.repo;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
}
