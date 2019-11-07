package fr.polytech.unice.credirama.mea;

import java.io.*;
import java.util.*;

import fr.polytech.unice.credirama.mea.entities.*;
import fr.polytech.unice.credirama.mea.entities.dto.AccountDTO;

import javax.validation.constraints.NotNull;

public class PrettyDumpWriter {

    private static FileWriter getInfoAccount(@NotNull AccountDTO a, @NotNull FileWriter file) {
        try {
            file.write("\t{\n" +
                    "\t\tID: " + a.getId() + "\n" +
                    "\t\tOwner: " + a.getOwnerId() + "\n" +
                    "\t\tBalance: " + a.getBalance() + "\n" +
                    "\t\tContract: " + a.getContract().name() + "\n" +
                    "\t\tTransactions: [ ");
            List<Long> transactions = a.getTransactions();
            for (int i = 0; i < transactions.size(); i++) {
                file.write("" + transactions.get(i));
                if (i < transactions.size() - 1) {
                    file.write(", ");
                }
            }
            file.write(" ]\n\t}\n");
        } catch (Exception e) {
            System.out.println("Error on writing");
        }
        return file;
    }

    private static FileWriter getInfoTransaction(@NotNull Transaction t, @NotNull FileWriter file) {
        try {
            file.write("\t{\n" +
                    "\t\tID:" + (int) t.getId() + "\n" +
                    "\t\tFrom: " + t.getFromId() + "\n" +
                    "\t\tTo: " + t.getToId() + "\n" +
                    "\t\tAmount: " + t.getAmount() + "\n" +
                    "\t}\n");
        } catch (Exception e) {
            System.out.println("Error on writing");
        }
        return file;
    }

    private static FileWriter getInfoClient(@NotNull Client c, @NotNull FileWriter file) {
        try {
            file.write("\t{\n" +
                    "\t\tID: " + c.getId() + "\n" +
                    "\t\tName: " + c.getName() + "\n" +
                    "\t\tAccounts: [ ");
            /*Iterator<Account> itAccounts = c.getAccountList().iterator();
            while (itAccounts.hasNext()) {
                file.write(itAccounts.next().getId());
                if (itAccounts.hasNext()) {
                    file.write(", ");
                }
            }*/
            for (int i = 0; i < c.getAccountList().size(); i++) {
                file.write("" + c.getAccountList().get(i).getId());
                if (i < c.getAccountList().size() - 1) {
                    file.write(", ");
                }
            }
            file.write(" ]\n\t}\n");
        } catch (Exception e) {
            System.out.println("Error on writing");
        }
        return file;
    }

    private static int parseTime(Calendar date) {
        if (date.get(Calendar.AM_PM) == 1) {
            //PM
            return date.get(Calendar.HOUR) + 12;
        } else {
            return date.get(Calendar.HOUR);
        }
    }

    public static String writePrettyDump(List<Client> clients, List<AccountDTO> accounts, List<Transaction> transactions) {
        //Create new prettyDump file
        FileWriter file = null;
        String path = "";
        try {
            //Naming the new file
            Calendar date = Calendar.getInstance();
            int year = date.get(Calendar.YEAR);
            int month = date.get(Calendar.MONTH);
            int day = date.get(Calendar.DATE);
            int hour = parseTime(date);
            int minute = date.get(Calendar.MINUTE);
            String fileLocation = System.getProperty("user.dir") + "/prettydump/";
            String fileName = year + "" + month + "" + day + "" + hour + "" + minute;
            //Creating the new file
            path = fileLocation + fileName + ".txt";
            File newPD = new File(path);
            file = new FileWriter(newPD);

            file.write("Clients: [ \n");
            for (Client c : clients) {
                file = getInfoClient(c, file);
            }
            file.write("]\nAccounts: [ \n");
            for (AccountDTO a : accounts) {
                file = getInfoAccount(a, file);
            }
            file.write("]\nTransactions: [ \n");
            for (Transaction t : transactions) {
                file = getInfoTransaction(t, file);
            }
            file.write("]\n");
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}