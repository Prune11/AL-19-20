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
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyseDataTest {

    @Autowired
    private AnalyseData analyseData;

    private List<Transaction> transactions;

    @Before
    public void setup() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(0, 0, 0, 50, TransactionType.TRANSFER, 0.5, Calendar.getInstance()));
        transactions.add(new Transaction(0, 0, 0, 50, TransactionType.TRANSFER, 1.2, Calendar.getInstance()));
        transactions.add(new Transaction(0, 0, 0, 50, TransactionType.TRANSFER, 0.2, Calendar.getInstance()));
        transactions.add(new Transaction(0, 0, 0, 50, TransactionType.TRANSFER, 0.9, Calendar.getInstance()));
        transactions.add(new Transaction(0, 0, 0, 50, TransactionType.TRANSFER, 2.5, Calendar.getInstance()));
        transactions.add(new Transaction(0, 0, 0, 50, TransactionType.TRANSFER, 0.4, Calendar.getInstance()));
        transactions.add(new Transaction(0, 0, 0, 50, TransactionType.TRANSFER, 0.8, Calendar.getInstance()));
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
