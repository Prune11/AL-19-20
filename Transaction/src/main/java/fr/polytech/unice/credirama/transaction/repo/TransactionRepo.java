package fr.polytech.unice.credirama.transaction.repo;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Integer> {

    List<Transaction> getTransactionsByFromId(Integer fromId);

    List<Transaction> getTransactionsByToId(Integer toId);

    List<Transaction> getTransactionsByFromIdOrToId(Integer fromId, Integer toId);

}
