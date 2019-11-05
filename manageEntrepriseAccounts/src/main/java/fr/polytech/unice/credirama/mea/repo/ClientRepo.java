package fr.polytech.unice.credirama.mea.repo;

import fr.polytech.unice.credirama.mea.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Integer> {
}