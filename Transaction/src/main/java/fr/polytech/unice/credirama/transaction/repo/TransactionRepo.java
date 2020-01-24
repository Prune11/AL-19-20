package fr.polytech.unice.credirama.transaction.repo;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Integer> {

    List<Transaction> getTransactionsByToId(Integer toId);
}
