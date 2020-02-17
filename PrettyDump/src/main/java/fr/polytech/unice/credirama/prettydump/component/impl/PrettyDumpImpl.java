package fr.polytech.unice.credirama.prettydump.component.impl;

import fr.polytech.unice.credirama.prettydump.component.PrettyDump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;


@Component
public class PrettyDumpImpl implements PrettyDump {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    private static final String TRANSACTION_URL = "http://localhost:8084";
    private static final String MEA_URL = "http://localhost:8081";

    @Override
    public String getPrettyDump() {
        String transactionURL = env.getProperty("TRANSACTION");
        System.out.println(transactionURL);
//        if (transactionURL == null || transactionURL.equals("")) transactionURL = TRANSACTION_URL;
        String meaURL = env.getProperty("MEA");
        System.out.println(meaURL);
//        if (meaURL == null || meaURL.equals("")) meaURL = MEA_URL;

        String globalresponse = "";
        String responseTransaction = "";
        String responseMea = "";
        responseTransaction = this.restTemplate.getForObject(transactionURL + "/prettyDump", String.class);
        responseMea = this.restTemplate.getForObject(meaURL + "/prettyDump", String.class);

        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DATE);
        int hour = parseTime(date);
        int minute = date.get(Calendar.MINUTE);
        int second = date.get(Calendar.SECOND);
        int millisecond = date.get(Calendar.MILLISECOND);

        String timestamp = year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second + "-" + millisecond;
        globalresponse = responseTransaction + "\n" + responseMea + "\n" + timestamp + "\n";
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
