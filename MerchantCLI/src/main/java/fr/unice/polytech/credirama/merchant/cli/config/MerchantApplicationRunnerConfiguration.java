package fr.unice.polytech.credirama.merchant.cli.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MerchantApplicationRunnerConfiguration {

    @Autowired
    private Shell shell;

    @Bean
    public CommandLineRunner exampleCommandLineRunner(ConfigurableEnvironment environment) {
        return new MerchantCommandLineRunner(shell, environment);
    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("Merchant-CLI:> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE).bold());
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Bean
    public ExitCodeExceptionMapper exitCodeExceptionMapper() {
        return exception -> {
            Throwable e = exception;
            while (e != null && !(e instanceof ExitRequest)) {
                e = e.getCause();
            }
            return e == null ? 1 : ((ExitRequest) e).status();
        };
    }
}