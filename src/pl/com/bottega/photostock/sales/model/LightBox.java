package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class LightBox implements Iterable<Product> {

    private String name;
    private Client client;
    private Collection<Product> items = new LinkedList<>();

    public LightBox(Client client, String name) {
        this.name = name;
        this.client = client;
    }

    public void add(Product product) {
        if (!product.isAvailable())
            throw new IllegalArgumentException(String.format("Product %s is not available", product.getNumber()));
        if (items.contains(product))
            throw new IllegalArgumentException(String.format("Product %s is already in LightBox %s", product.getNumber(), name));
        items.add(product);
    }

    public void remove(Product product) {
        if (!items.contains(product))
            throw new IllegalArgumentException(String.format("Product %s is not in LightBox %s", product.getNumber(), name));
        items.remove(product);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    @Override
    public Iterator<Product> iterator() {
        return items.iterator();
    }

    public Client getOwner() {
        return client;
    }

    public static LightBox joined(Client client, String name, LightBox... lightboxes) {
        LightBox resultLightBox = new LightBox(client, name);
        resultLightBox.join(lightboxes);
        return resultLightBox;
    }

    private void join(LightBox[] lightboxes) {
        for (LightBox lightbox : lightboxes)
            for (Product product : lightbox) {
                if (!items.contains(product) && product.isAvailable())
                    items.add(product);
            }

    }
}
