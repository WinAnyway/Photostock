package pl.com.bottega.photostock.sales.model;

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
