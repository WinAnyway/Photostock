package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Arrays;
import java.util.Collection;

public class ConsoleApplication {

    public static void main(String[] args) {
        Collection<String> tags = Arrays.asList("przyroda", "motoryzacja");
        Picture picture1 = new Picture("BMW", tags, Money.valueOf(3));
        Picture picture2 = new Picture("Mercedes", tags, Money.valueOf(2));
        Picture picture3 = new Picture("Porshe", tags, Money.valueOf(4));

        Client client = new Client("Johny X", new Address(), Money.valueOf(100));

        Reservation reservation = new Reservation(client);

        reservation.add(picture1);
        reservation.add(picture2);
        reservation.add(picture3);
        System.out.println("After adding items count: " + reservation.getItemsCount());

        reservation.getItemsCount();

        Offer offer = reservation.generateOffer();

        boolean canAfford = client.canAfford(offer.getTotalCost());
        System.out.println("Client can afford: " + String.valueOf(canAfford));

        if (canAfford) {
            client.charge(offer.getTotalCost(), "Test purchase");
            Purchase purchase = new Purchase(client, picture1, picture2, picture3);
            System.out.println("Client purchased: " + purchase.getItemsCount() + " products");
            System.out.println("Total cost: " + offer.getTotalCost());
        }
        else {
            System.out.println("Client cannot afford");
        }
    }
}
