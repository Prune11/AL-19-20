package fr.polytech.unice.credirama.mea.config;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.component.impl.ManageEnterpriseAccountImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Bean
    public ManageEnterpriseAccount manageEnterpriseAccount() {
        return new ManageEnterpriseAccountImpl();
    }

}
