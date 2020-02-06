package fr.polytech.unice.creadirama.transaction.unit;

import fr.polytech.unice.creadirama.transaction.AbstractStep;
import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionsBtw2DatesResponse;
import fr.polytech.unice.credirama.transaction.repo.TransactionRepo;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionComponentTest extends AbstractStep {

    private Transaction transaction;
    private Transaction transaction2;
    private Transaction transaction3;
    private Transaction transaction4;

    @Autowired
    private TransactionComponent transactionComponent;

    @Autowired
    private TransactionRepo transactionRepo;

    @Before
    public void initialize() {
        DateTime dateTime = new DateTime(2020,1,31,0,0); //31/1/2020
        transaction = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, dateTime);
        this.transactionRepo.save(transaction);
        dateTime = dateTime.plusHours(1); //31/1/2020
        transaction2 = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, dateTime);
        this.transactionRepo.save(transaction2);
        dateTime = dateTime.plusDays(1); //1/2/2020
        transaction3 = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, dateTime);
        this.transactionRepo.save(transaction3);
        dateTime = dateTime.plusDays(5); //6/2/2020
        transaction4 = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, dateTime);
        this.transactionRepo.save(transaction4);
    }

    @Test
    public void testTransaction(){
        DateTime dateFrom = new DateTime(2020, 1 , 28, 12, 0); //28/1/2020 midi
        DateTime dateTo = new DateTime(2020, 2 , 2, 12, 0); //2/2/2020 midi
        TransactionsBtw2DatesResponse response = this.transactionComponent.getAllReceivedTransactionsByUserIdBetweenToDates(2, dateFrom, dateTo);
        assertEquals(6, response.getTransactionPerDay().keySet().size());
        for (DateTime dateTime : response.getTransactionPerDay().keySet()) {
            if (dateTime.getDayOfMonth() == 31){
                assertEquals(2, response.getTransactionPerDay().get(dateTime).size());
                assertTrue(response.getTransactionPerDay().get(dateTime).contains(transaction));
                assertTrue(response.getTransactionPerDay().get(dateTime).contains(transaction2));
            } else if (dateTime.getDayOfMonth() == 1){
                assertEquals(1, response.getTransactionPerDay().get(dateTime).size());
                assertTrue(response.getTransactionPerDay().get(dateTime).contains(transaction3));
            } else {
                assertEquals(0, response.getTransactionPerDay().get(dateTime).size());
            }
        }
    }

}
