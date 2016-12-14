package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Arrays;
import java.util.Collection;

public class LightBoxTest {
    public static void main(String[] args) {
        Collection<String> tags = Arrays.asList("przyroda", "motoryzacja");
        Picture picture1 = new Picture("BMW", tags, Money.valueOf(3));
        Picture picture2 = new Picture("Mercedes", tags, Money.valueOf(2));
        Picture picture3 = new Picture("Porshe", tags, Money.valueOf(4));

        Client client1 = new Client("Janek", new Address(), Money.ZERO);
        Client client2 = new Client("Franek", new Address(), Money.valueOf(10));

        LightBox lb1 = new LightBox(client1, "Samochody");
        LightBox lb2 = new LightBox(client1, "Wyścigowe Samochody");
        LightBox lb3 = new LightBox(client2, "BMW");

        lb1.add(picture1);
        lb1.add(picture2);
        lb1.add(picture3);

        lb2.add(picture1);

        lb3.add(picture3);

        picture1.deactivate();

        printLightboxes(lb1, lb2, lb3);

        LightBox lb4 = LightBox.joined(client1, "Joined lightbox", lb1, lb2, lb3);
        System.out.println("Joined lightbox");
        printLightboxes(lb4);
    }

    private static void printLightboxes(LightBox... lightBoxes) {
        int nr = 1;
        for (LightBox lightBox : lightBoxes) {
            System.out.println(String.format("%d. %s - %s", nr, lightBox.getName(), lightBox.getOwner().getName()));
            printLightBox(lightBox);
            nr++;
        }
    }

    private static void printLightBox(LightBox lightBox) {
        for(Picture picture : lightBox) {
            System.out.println(String.format("%s%s | %s",
                    (picture.isActive() ? "" : "X "),
                    picture.getNumber(),
                    picture.calculatePrice(lightBox.getOwner())));
        }
    }
}
