package fr.polytech.unice.credirama.mea.component;

import java.io.*;
import java.util.*;
import fr.polytech.unice.credirama.mea.entities.*;

import javax.validation.constraints.NotNull;

public class PrettyDumpFormat {
    private static void getInfoAccount(@NotNull Account a, @NotNull FileWriter file) {
        try {
            file.write("\t{\n" +
                    "\t\tID: " + a.getId() + "\n" +
                    "\t\tBalance: " + a.getBalance() + "\n" +
                    "\t\tContract: " + a.getContract() + "\n" +
                    "\t\tTransactions: [");
            Iterator<Transaction> itTransactions = a.getTransactions().iterator();
            while (itTransactions.hasNext()) {
                file.write((int) itTransactions.next().getId());
                if (itTransactions.hasNext()) {
                    file.write(", ");
                }
            }
            file.write(" ]\n\t}\n");
        }
        catch (Exception e) {
            System.out.println("Error on writing");
        }
    }

    private static void getInfoTransaction (@NotNull Transaction t, @NotNull FileWriter file) {
        try {
            file.write("\t{\n" +
                    "\t\tID:" + (int) t.getId() + "\n" +
                    "\t\tFrom: " + t.getAccountFrom() + "\n" +
                    "\t\tTo: " + t.getAccountTo() + "\n" +
                    "\t\tAmount: " + t.getAmount() +"\n" +
                    "\t}\n");
        }
        catch (Exception e) {
            System.out.println("Error on writing");
        }
    }

    private static void getInfoClient(@NotNull Client c, @NotNull  FileWriter file) {
        try {
            file.write("\t{\n" +
                    "\t\tID: " + c.getId() + "\n" +
                    "\t\tName: " + c.getName() + "\n" +
                    "\t\tAccounts: [ ");
            Iterator<Account> itAccounts = c.getAccountList().iterator();
            while (itAccounts.hasNext()) {
                file.write(itAccounts.next().getId());
                if (itAccounts.hasNext()) {
                    file.write(", ");
                }
            }
            file.write(" ]\n\t}\n");
        }
        catch (Exception e) {
            System.out.println("Error on writing");
        }
    }

    private static int parseTime(Calendar date) {
        if (date.get(Calendar.AM_PM) == 1) {
            //PM
            return date.get(Calendar.HOUR) + 12;
        } else {
            return date.get(Calendar.HOUR);
        }
    }

    private static void writePrettyDump(List<Client> clients, List<Account> accounts, List<Transaction> transactions) {
        //Create new prettyDump file
        FileWriter file = null;
        try {
            //Naming the new file
            Calendar date = Calendar.getInstance();
            int year = date.get(Calendar.YEAR);
            int month = date.get(Calendar.MONTH);
            int day = date.get(Calendar.DATE);
            int hour = parseTime(date);
            int minute = date.get(Calendar.MINUTE);
            String fileLocation = System.getProperty("user.dir") + "/prettydump/";
            String fileName = String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + "_" + String.valueOf(hour) + String.valueOf(minute);
            //Creating the new file
            File newPD = new File(fileLocation + fileName + ".txt");
            file = new FileWriter(newPD);

            file.write("Clients: [ \n");
            for (Client c : clients) {
                getInfoClient(c, file);
            }
            file.write("]\nAccounts: [ \n");
            for (Account a : accounts) {
                getInfoAccount(a, file);
            }
            file.write("]\nTransactions: [ \n");
            for (Transaction t: transactions) {
                getInfoTransaction(t, file);
            }
            file.write("]\n");
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        Client client = new Client("Michel");
        Client elena = new Client ("Elena");
        Account account = new Account(client, Contract.IRON, 5);
        Account accElena = new Account(elena, Contract.DIAMOND, 3);
        Transaction virement = new Transaction(elena.getId(), client.getId(), 3);
        List<Client> lc = new ArrayList<Client>();
        lc.add(elena);
        lc.add(client);
        List<Account> la = new ArrayList<Account>();
        la.add(account);
        la.add(accElena);
        List<Transaction> lt = new ArrayList<>();
        lt.add(virement);
        writePrettyDump(lc, la, lt);
    }*/

}