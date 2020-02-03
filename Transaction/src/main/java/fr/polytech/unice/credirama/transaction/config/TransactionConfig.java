package fr.polytech.unice.credirama.transaction.config;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.component.impl.TransactionComponentImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    public TransactionComponent transactionComponent() {
        return new TransactionComponentImpl();
    }

}
