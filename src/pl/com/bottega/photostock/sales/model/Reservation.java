package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class Reservation {

    private Client client;
    private Collection<Product> items;

    public Reservation(Client client){
        this.client = client;
        this.items = new LinkedList<>();
    }

    public void add(Product product) {
        if(items.contains(product))
            throw new IllegalArgumentException(String.format("Product %s is already in this reservation", product.getNumber()));
        if(!product.isAvailable())
            throw new IllegalArgumentException(String.format("Product %s is not available", product.getNumber()));
        items.add(product);
    }

    public void remove(Product product) {
        if(!items.contains(product))
            throw new IllegalArgumentException(String.format("Product %s is not added to this reservation.", product.getNumber()));
        items.remove(product);
    }

    public Offer generateOffer() {
        return new Offer(client, getActiveItems());
    }

    private Collection<Product> getActiveItems() {
        Collection<Product> activeItems = new HashSet<>();
        for(Product item : items){
            if(item.isActive())
                activeItems.add(item);
        }
        return activeItems;
    }

    public int getItemsCount() {
        return items.size();
    }
}
