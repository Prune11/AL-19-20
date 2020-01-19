package fr.polytech.unice.credirama.prettydump.component.impl;

import fr.polytech.unice.credirama.prettydump.component.PrettyDump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;


@Component
public class PrettyDumpImpl implements PrettyDump {

    @Autowired
    private RestTemplate restTemplate;

    private static final String TRANSACTION_URL = "http://localhost:8085";
    private static final String MEA_URL = "http://localhost:8081";

    @Override
    public String getPrettyDump() {
        String globalresponse = "";
        String responseTransaction = "";
        String responseMea = "";
        responseTransaction = this.restTemplate.getForObject(TRANSACTION_URL + "/prettydump", String.class);
        responseMea = this.restTemplate.getForObject(MEA_URL + "/prettydump", String.class);

        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DATE);
        int hour = parseTime(date);
        int minute = date.get(Calendar.MINUTE);
        int second = date.get(Calendar.SECOND);
        int millisecond = date.get(Calendar.MILLISECOND);

        String timestamp = year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second + "-" + millisecond;


        return globalresponse;
    }

    private int parseTime(Calendar date) {
        if (date.get(Calendar.AM_PM) == 1) {
            //PM
            return date.get(Calendar.HOUR) + 12;
        } else {
            return date.get(Calendar.HOUR);
        }
    }
}
