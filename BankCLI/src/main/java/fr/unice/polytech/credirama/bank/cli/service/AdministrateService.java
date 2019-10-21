package fr.unice.polytech.credirama.bank.cli.service;

import fr.unice.polytech.credirama.bank.cli.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(url = "http://localhost:8081/administration")
public interface AdministrateService {

    Client createAccount(String companyName);

}
