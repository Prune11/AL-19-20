package fr.unice.polytech.credirama.bank.cli.service;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TranslationService {

    public String translate(String source, Locale from, Locale to) {
        return source;
    }

}
