package fr.polytech.unice.creadirama.transaction.config;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.component.impl.TransactionComponentImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class TransactionContextConfiguration {

    @Bean
    public TransactionComponent transactionComponent() {
        return new TransactionComponentImpl();
    }
}
