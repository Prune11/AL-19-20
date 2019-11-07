package fr.polytech.unice.credirama.mea.entities.dto;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private int id;

    private String name;

    private List<Integer> accountIds;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.accountIds = new ArrayList<>();
        for(Account account : client.getAccountList()){
            accountIds.add(account.getId());
        }
    }
}
