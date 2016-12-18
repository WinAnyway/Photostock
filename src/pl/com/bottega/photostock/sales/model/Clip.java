package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.model.money.Money;

public class Clip extends AbstractProduct {

    private static final long FIVE_MINUTES = 1000L * 60 * 5;
    private long length;

    public Clip(String number, String name, long length, Money catalogPrice, boolean active) {
        super(active, number, name, catalogPrice);
        this.length = length;
    }

    public Clip(String number, String name, long length, Money catalogPrice) {
        this(number, name, length, catalogPrice, true);
    }

    @Override
    public Money calculatePrice(Client client) {
        if (length > FIVE_MINUTES)
            return catalogPrice.multiply(2);

        return catalogPrice;
    }
}
