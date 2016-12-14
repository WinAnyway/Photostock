package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;

public class Offer {

    private Collection<Picture> items;
    private Client client;

    public Offer(Client client, Collection<Picture> items) {
        this.client = client;
        this.items = new HashSet<>(items);
    }

    public boolean sameAs(Offer other, Money money) {
        return false;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Money getTotalCost() {
        Money totalCost = Money.ZERO;
        for(Picture item : items){
            totalCost = totalCost.add(item.calculatePrice(client));
        }
        return totalCost;
    }
}
