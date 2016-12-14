package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Arrays;
import java.util.Collection;

public class InMemoryLightBoxRepositoryTest {

    public static void main(String[] args) {
        Collection<String> tags = Arrays.asList("przyroda", "motoryzacja");
        InMemoryLightBoxRepository repository = new InMemoryLightBoxRepository();

        Picture picture1 = new Picture("BMW", tags, Money.valueOf(3));
        Picture picture2 = new Picture("Mercedes", tags, Money.valueOf(2));
        Picture picture3 = new Picture("Porshe", tags, Money.valueOf(4));

        Client client1 = new Client("Janek", new Address(), Money.ZERO);
        Client client2 = new Client("Franek", new Address(), Money.valueOf(10));
        Client client3 = new Client("Kacper", new Address(), Money.valueOf(5));


        LightBox lb1 = new LightBox(client1, "Samochody");
        LightBox lb2 = new LightBox(client1, "Wyścigowe Samochody");
        LightBox lb3 = new LightBox(client2, "BMW");
        LightBox lb4 = new LightBox(client3, "Krajobrazy");


        lb1.add(picture1);
        lb1.add(picture2);
        lb1.add(picture3);
        lb2.add(picture1);
        lb3.add(picture3);
        lb4.add(picture1);
        lb4.add(picture2);
        lb4.add(picture3);

        repository.put(lb1);
        repository.put(lb2);
        repository.put(lb3);
        repository.put(lb4);

//        repository.put(lb1);

        printLightBoxes(repository.getFor(client1));
        printLightBoxes(repository.getFor(client2));
        printLightBoxes(repository.getFor(client3));
    }
    private static void printLightBoxes(Collection<LightBox> lightBoxes) {
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
