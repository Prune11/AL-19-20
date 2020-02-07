package fr.polytech.unice.credirama.mea.entities;


import fr.polytech.unice.credirama.mea.entities.contract.Contract;
import fr.polytech.unice.credirama.mea.entities.dto.MEAAddTransactionRequest;
import fr.polytech.unice.credirama.mea.entities.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class AccountTest {
    public Account account1 = new Account(new Client("bob"), Contract.WOOD, 10);

    private int numberOfTransactions = 0;

    @Test
    public void addTransactionTest() {
        account1.setId(3);
        MEAAddTransactionRequest meaAddTransactionRequest = new MEAAddTransactionRequest(1, 0, 3, 10);
        assertEquals(0.5, account1.addTransaction(meaAddTransactionRequest), 0);
        numberOfTransactions++;
        meaAddTransactionRequest = new MEAAddTransactionRequest(2, 0, 3, 0.3);
        assertEquals(0.03, account1.addTransaction(meaAddTransactionRequest), 0);
        numberOfTransactions++;

      /*  account1.setContract(Contract.STONE);

        meaAddTransactionRequest = new MEAAddTransactionRequest(3, 0, 3, 10);
        assertEquals(0.5, account1.addTransaction(meaAddTransactionRequest), 0);
        numberOfTransactions++;

        meaAddTransactionRequest = new MEAAddTransactionRequest(4, 0, 3, 10);
        account1.addTransaction(meaAddTransactionRequest);
        numberOfTransactions++;

        meaAddTransactionRequest = new MEAAddTransactionRequest(5, 0, 3, 10);
        account1.addTransaction(meaAddTransactionRequest);
        numberOfTransactions++;

        meaAddTransactionRequest = new MEAAddTransactionRequest(6, 0, 3, 10);
        numberOfTransactions++;
        assertEquals(0.1, account1.addTransaction(meaAddTransactionRequest), 0);*/
    }
}
