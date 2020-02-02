package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clients", produces = "application/json")
public class ClientAdministrationController {

    @Qualifier("manageEnterpriseAccount")
    @Autowired
    private ManageEnterpriseAccount manageEnterpriseAccount;

    @GetMapping("")
    public List<Client> getAllClients(){
        return this.manageEnterpriseAccount.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable(name = "id") Integer id){
        return this.manageEnterpriseAccount.getClientById(id);
    }

    @PostMapping("")
    public Client createClient(@RequestBody String owner){
        return this.manageEnterpriseAccount.createClient(owner);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable(name = "id") Integer id){
        return this.manageEnterpriseAccount.deleteClient(id);
    }

    @DeleteMapping("")
    public String deleteAllClients(){
        return this.manageEnterpriseAccount.deleteAllClients();
    }


}

