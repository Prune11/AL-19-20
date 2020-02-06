package fr.polytech.unice.creadirama.transaction.unit;

import fr.polytech.unice.creadirama.transaction.AbstractStep;
import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.component.impl.TransactionComponentImpl;
import fr.polytech.unice.credirama.transaction.entities.CrediramaDate;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionsBtw2DatesResponse;
import fr.polytech.unice.credirama.transaction.repo.TransactionRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionComponentTest extends AbstractStep {

    @TestConfiguration
    static class TransactionContextConfiguration {

        @Bean
        public TransactionComponent transactionComponent() {
            return new TransactionComponentImpl();
        }
    }

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
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, Calendar.JANUARY,31,12,0); //31/1/2020
        transaction = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, gregorianCalendar);
        this.transactionRepo.save(transaction);
        gregorianCalendar.add(Calendar.HOUR_OF_DAY,1); //31/1/2020
        transaction2 = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, gregorianCalendar);
        this.transactionRepo.save(transaction2);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH,1); //1/2/2020
        transaction3 = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, gregorianCalendar);
        this.transactionRepo.save(transaction3);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH,5); //6/2/2020
        transaction4 = new Transaction(1, 2, 5.0, 0.5, TransactionType.TRANSFER, gregorianCalendar);
        this.transactionRepo.save(transaction4);
    }

    @Test
    public void testTransaction(){
        GregorianCalendar dateFrom = new GregorianCalendar(2020, Calendar.JANUARY,28,12,0); //28/1/2020
        GregorianCalendar dateTo = new GregorianCalendar(2020, Calendar.FEBRUARY,2,12,0); //2/2/2020
        TransactionsBtw2DatesResponse response = this.transactionComponent.getAllReceivedTransactionsByUserIdBetweenToDates(2, dateFrom, dateTo);
        assertEquals(6, response.getTransactionPerDay().keySet().size());
        for (CrediramaDate crediramaDate : response.getTransactionPerDay().keySet()) {
            if (crediramaDate.getDay() == 31){
                assertEquals(2, response.getTransactionPerDay().get(crediramaDate).size());
                assertTrue(response.getTransactionPerDay().get(crediramaDate).contains(transaction));
                assertTrue(response.getTransactionPerDay().get(crediramaDate).contains(transaction2));
            } else if (crediramaDate.getDay() == 1){
                assertEquals(1, response.getTransactionPerDay().get(crediramaDate).size());
                assertTrue(response.getTransactionPerDay().get(crediramaDate).contains(transaction3));
            } else {
                assertEquals(0, response.getTransactionPerDay().get(crediramaDate).size());
            }
        }
    }

}
