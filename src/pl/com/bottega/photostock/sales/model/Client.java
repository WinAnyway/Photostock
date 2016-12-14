package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.LinkedList;

public class Client {

    private String name;
    private Address address;
    private ClientStatus status;
    private Money balance;
    private Money creditLimit;
    private Collection<Transaction> transactions;

    public Client(String name, Address address, ClientStatus status, Money initialBalance, Money creditLimit) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.balance = initialBalance;
        this.creditLimit = creditLimit;
        this.transactions = new LinkedList<>();
        if (!initialBalance.equals(Money.ZERO))
            this.transactions.add(new Transaction(initialBalance, "Opening account"));
    }

    public Client(String name, Address address, Money balance) {
        this(name, address, ClientStatus.REGULAR, balance, Money.ZERO);
    }

    public boolean canAfford(Money money) {
        return balance.gte(money);
    }

    public void charge(Money money, String reason) {
        if(money.lte(Money.ZERO))
            throw new IllegalArgumentException("Negative charge amount prohibited");

        if (canAfford(money)) {
            Transaction chargeTransaction = new Transaction(money.opposite(), reason);
            transactions.add(chargeTransaction);
            balance = balance.subtract(money);
        } else {
            String template = "Client balance is %s and requested amount was %s";
            String message = String.format(template, balance, money);
            throw new CantAffordException(message);
        }
    }

    public void recharge(Money money) {
        if(money.lte(Money.ZERO))
            throw new IllegalArgumentException("Negative recharge amount prohibited");

        Transaction transaction = new Transaction(money, "Recharge account");
        transactions.add(transaction);
        balance = balance.add(money);
    }

    public String getName() {
        return name;
    }
}
