package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashSet;

public class Picture {

    private String number;
    private Collection<String> tags;
    private Money catalogPrice;
    private boolean active;
    private Client reservationOwner;
    private Client buyer;

    public Picture(String number, Collection<String> tags, Money catalogPrice, boolean active) {
        this.number = number;
        this.tags = new HashSet<String>(tags);
        this.catalogPrice = catalogPrice;
        this.active = active;
    }

    public Picture(String number, Collection<String> tags, Money catalogPrice) {
        this(number, tags, catalogPrice, true);
    }

    public Money calculatePrice(Client client) {
        return catalogPrice;
    }

    public boolean isAvailable() {
        return active && !isSold() && !isReserved();
    }

    private boolean isReserved() {
        return reservationOwner != null;
    }

    private boolean isSold() {
        return buyer != null;
    }

    public void reservedPer(Client client) {
        if (!isAvailable())
            throw new IllegalStateException(String.format("{Picture %s is not available for reservation", getNumber()));
        reservationOwner = client;
    }

    public void unreservedPer(Client client) {
        ensureReservedBy(client);
        reservationOwner = null;
    }

    private void ensureReservedBy(Client client) {
        if (!isReservedBy(client))
            throw new IllegalArgumentException(String.format("Picture %s is not reserved by %s", getNumber(), client.getName()));
    }

    private boolean isReservedBy(Client client) {
        return isReserved() && client.equals(reservationOwner);
    }

    public boolean isActive() {
        return active;
    }

    public void soldPer(Client client) {
        ensureReservedBy(client);
        buyer = client;
        unreservedPer(client);
    }

    public String getNumber() {
        return number;
    }

/*
    @Override
    public boolean equals(Object other) {

        if (this == o) return true;
        if (other == null || !(other instanceof Picture))
            return false;
        Picture otherPicture = (Picture) other;
        return (otherPicture.number == number) ||
                (otherPicture.number != null && otherPicture.number.equals(number));
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        return number != null ? number.equals(picture.number) : picture.number == null;

    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    public void deactivate() {
        active = false;
    }
}
