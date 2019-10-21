package fr.polytech.unice.credirama.mea.repo;

import fr.polytech.unice.credirama.mea.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
}
