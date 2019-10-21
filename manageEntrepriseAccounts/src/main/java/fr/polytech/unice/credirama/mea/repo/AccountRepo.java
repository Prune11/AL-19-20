package fr.polytech.unice.credirama.mea.repo;

import fr.polytech.unice.credirama.mea.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Integer> {
}