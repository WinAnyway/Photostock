package pl.com.bottega.photostock.sales.model.product;

import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.money.Money;

public interface Product {
    Money calculatePrice(Client client);

    boolean isAvailable();

    void reservedPer(Client client);

    void unreservedPer(Client client);

    boolean isActive();

    void soldPer(Client client);

    String getNumber();

    String getName();

    void deactivate();

    default void ensureAvailable(){
        if(!isAvailable())
            throw new ProductNotAvailableException(this);
    }
}
