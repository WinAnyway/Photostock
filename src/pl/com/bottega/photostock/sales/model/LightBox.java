package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class LightBox implements Iterable<Picture> {

    private String name;
    private Client client;
    private Collection<Picture> items = new HashSet<>();

    public LightBox(Client client, String name) {
        this.name = name;
        this.client = client;
    }

    public void add(Picture picture) {
        if (!picture.isAvailable())
            throw new IllegalArgumentException(String.format("Picture %s is not available", picture.getNumber()));
        if (items.contains(picture))
            throw new IllegalArgumentException(String.format("Picture %s is already in LightBox %s", picture.getNumber(), name));
        items.add(picture);
    }

    public void remove(Picture picture) {
        if (!items.contains(picture))
            throw new IllegalArgumentException(String.format("Picture %s is not in LightBox %s", picture.getNumber(), name));
        items.remove(picture);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    @Override
    public Iterator<Picture> iterator() {
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
            for (Picture picture : lightbox) {
                if (!items.contains(picture) && picture.isAvailable())
                    items.add(picture);
            }

    }
}
