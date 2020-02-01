package fr.polytech.unice.creadirama.analyse.component;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import fr.polytech.unice.creadirama.analyse.entity.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyseDataTest {

    @Autowired
    private AnalyseData analyseData;

    private ArrayList<Transaction> transactions;

    @Before
    public void setup() {
        transactions = new ArrayList<>();
        transactions.add(new Transaction(0, 0, 50, 0.5, TransactionType.TRANSFER, new GregorianCalendar()));
        transactions.add(new Transaction(0, 0, 50, 1.2, TransactionType.TRANSFER, new GregorianCalendar()));
        transactions.add(new Transaction(0, 0, 50, 0.2, TransactionType.TRANSFER, new GregorianCalendar()));
        transactions.add(new Transaction(0, 0, 50,  0.9, TransactionType.TRANSFER, new GregorianCalendar()));
        transactions.add(new Transaction(0, 0, 50,  2.5, TransactionType.TRANSFER, new GregorianCalendar()));
        transactions.add(new Transaction(0, 0, 50, 0.4, TransactionType.TRANSFER, new GregorianCalendar()));
        transactions.add(new Transaction(0, 0, 50, 0.8, TransactionType.TRANSFER, new GregorianCalendar()));
    }

    @Test
    public void sumFeesPerDayTest() {
        assertEquals(6.5, analyseData.sumFeesPerDay(transactions), 0.001);
    }

    @Test
    public void avgFeePerDayTest() {
        assertEquals(0.928, analyseData.avgFeePerDay(transactions), 0.005);
    }

    @Test
    public void minTransactionFeeTest() {
        assertEquals(0.2, analyseData.minTransactionFee(transactions).getFeeAmount(), 0.005);
    }

    @Test
    public void maxTransactionFeeTest() {
        assertEquals(2.5, analyseData.maxTransactionFee(transactions).getFeeAmount(), 0.005);
    }

    @Test
    public void sumBetweenTwoDateTest() {

    }
}
