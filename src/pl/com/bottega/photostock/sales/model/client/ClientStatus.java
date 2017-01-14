package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.money.Money;

public enum ClientStatus {

    STANDARD("Standardowy"),
    VIP("Vip"),
    GOLD("Złoty"),
    SILVER("Srebrny"),
    PLATINUM("Platynowy");

    private String statusName;

    ClientStatus(String name){
        this.statusName = name;
    }

    public String getStatusName(){
        return statusName;
    }

    public static class VIPClient extends Client {

        private Money creditLimit;

        public VIPClient(String name, Address address, Money balance, Money creditLimit) {
            super(name, address, VIP, balance);
            this.creditLimit = creditLimit;
        }

        @Override
        public boolean canAfford(Money money) {
            return balance.add(creditLimit).gte(money);
        }
    }
}
