package fr.unice.polytech.credirama.merchant.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

//import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
public class MerchantCLI {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        SpringApplication app = new SpringApplication(MerchantCLI.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
    }
}
