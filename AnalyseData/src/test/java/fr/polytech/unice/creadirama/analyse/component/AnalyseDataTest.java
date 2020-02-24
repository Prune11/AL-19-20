package fr.polytech.unice.creadirama.analyse.component;

import fr.polytech.unice.creadirama.analyse.dto.SimulationDTO;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import fr.polytech.unice.creadirama.analyse.entity.TransactionType;
import fr.polytech.unice.creadirama.analyse.entity.contract.Contract;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyseDataTest {

    @Autowired
    private AnalyseData analyseData;

    private List<Transaction> transactions;

    private Map<Calendar, List<Transaction>> transactionPerDay;

    private FeeBtw2Day feeBtw2Day;

    @Before
    public void setup() {
        transactions = new ArrayList<>();

        transactions.add(new Transaction(0, 0, 50, 0.5, TransactionType.TRANSFER, new DateTime()));
        transactions.add(new Transaction(0, 0, 50, 1.2, TransactionType.TRANSFER, new DateTime()));
        transactions.add(new Transaction(0, 0, 50, 0.2, TransactionType.TRANSFER, new DateTime()));
        transactions.add(new Transaction(0, 0, 50, 0.9, TransactionType.TRANSFER, new DateTime()));
        transactions.add(new Transaction(0, 0, 50, 2.5, TransactionType.TRANSFER, new DateTime()));
        transactions.add(new Transaction(0, 0, 50, 0.4, TransactionType.TRANSFER, new DateTime()));
        transactions.add(new Transaction(0, 0, 50, 0.8, TransactionType.TRANSFER, new DateTime()));

        feeBtw2Day = new FeeBtw2Day();
        transactionPerDay = new HashMap<>();
        List<Transaction> list = new ArrayList<>();

        Calendar calendar = GregorianCalendar.getInstance();
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
//TODO
    }

    @Test
    public void simulationTest() {
        Map<DateTime, List<Transaction>> transactionData = new HashMap<>();
        DateTimeFormatter format = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        /*
        Time simulation for Stone contract
         */
        for (int i = 0; i < transactions.size(); i++) {
            DateTime temp = format.parseDateTime(transactions.get(i).getTimeStamp());
            System.out.println(i);
            if (i == transactions.size() - 1) {
                temp = temp.plusMillis(i);
            } else {
                temp = temp.minusMillis(i);
            }
            System.out.println(temp);
            transactions.get(i).setTimeStamp(format.print(temp));
            System.out.println(transactions.get(i).getTimeStamp());

        }
        transactionData.put(DateTime.now(), transactions);
        Map<String, SimulationDTO> simulation = analyseData.simulationWithAnotherContract(transactionData);
        SimulationDTO wood = simulation.get(Contract.WOOD.name());
        SimulationDTO stone = simulation.get(Contract.STONE.name());
        SimulationDTO iron = simulation.get(Contract.IRON.name());
        SimulationDTO diamond = simulation.get(Contract.DIAMOND.name());
        //TODO test sur toutes les valeurs mais ça marche a priori vu que la moyenne change et que le reste des méthodes est fonctionnel
        //WOOD
        assertEquals(2.5, wood.getTotalAvg(), 0);
        //STONE
        assertEquals(3.21, stone.getTotalAvg(), 0);
        //IRON
        assertEquals(2.5, iron.getTotalAvg(), 0);
        //DIAMOND
        assertEquals(0.5, diamond.getTotalAvg(), 0);


    }
}
