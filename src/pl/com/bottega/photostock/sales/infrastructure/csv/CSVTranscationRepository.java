package pl.com.bottega.photostock.sales.infrastructure.csv;

import com.sun.deploy.util.StringUtils;
import pl.com.bottega.photostock.sales.model.money.Money;
import pl.com.bottega.photostock.sales.model.purchase.Transaction;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class CSVTranscationRepository {

    private String folderPath;

    public CSVTranscationRepository(String folderPath) {
        this.folderPath = folderPath;
    }

    public void saveTransactions(String clientNumber, Collection<Transaction> transactions) {
        try (PrintWriter printWriter = new PrintWriter(getRepositoryPath(clientNumber))
        ) {
            for (Transaction transaction : transactions) {
                String[] components = {
                        transaction.getValue().toString(),
                        transaction.getDescription(),
                        transaction.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME)
                };
                printWriter.println(StringUtils.join(Arrays.asList(components), ","));
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public Collection<Transaction> getTransactions(String clientNumber) {
        Collection<Transaction> transactions = new LinkedList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(getRepositoryPath(clientNumber)))
        ) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] transactionElements = line.split(",");
                transactions.add(new Transaction(Money.valueOf(transactionElements[0]), transactionElements[1]));
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return transactions;
    }

    private String getRepositoryPath(String clientNumber) {
        return folderPath + File.separator + "client-" + clientNumber + "-transactions.csv";
    }
}
