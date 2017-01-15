package pl.com.bottega.photostock.sales.model.purchase;

import pl.com.bottega.photostock.sales.model.money.Money;

import java.time.LocalDateTime;

public class Transaction {

    private Money value;
    private String description;
    private LocalDateTime timestamp;

    public Transaction(Money value, String description) {
        this.value = value;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public Money getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
