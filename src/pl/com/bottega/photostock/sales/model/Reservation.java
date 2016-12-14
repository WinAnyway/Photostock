package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;

public class Reservation {

    private Client client;
    private Collection<Picture> items;

    public Reservation(Client client){
        this.client = client;
        this.items = new HashSet<>();
    }

    public void add(Picture picture) {
        if(items.contains(picture))
            throw new IllegalArgumentException(String.format("Picture %s is already in this reservation", picture.getNumber()));
        if(!picture.isAvailable())
            throw new IllegalArgumentException(String.format("Picture %s is not available", picture.getNumber()));
        items.add(picture);
    }

    public void remove(Picture picture) {
        items.remove(picture);
    }

    public Offer generateOffer() {
        return new Offer(client, getActiveItems());
    }

    private Collection<Picture> getActiveItems() {
        Collection<Picture> activeItems = new HashSet<>();
        for(Picture item : items){
            if(item.isActive())
                activeItems.add(item);
        }
        return activeItems;
    }

    public int getItemsCount() {
        return items.size();
    }
}
