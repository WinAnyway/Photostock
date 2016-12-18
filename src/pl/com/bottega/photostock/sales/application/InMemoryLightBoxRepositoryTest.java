package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.Collection;

public class InMemoryLightBoxRepositoryTest {

    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        InMemoryLightBoxRepository repository = new InMemoryLightBoxRepository();


        Product product1 = productRepository.get("1");
        Product product2 = productRepository.get("2");
        Product product3 = productRepository.get("3");


        Client client1 = new Client("Janek", new Address(), Money.ZERO);
        Client client2 = new Client("Franek", new Address(), Money.valueOf(10));
        Client client3 = new Client("Kacper", new Address(), Money.valueOf(5));


        LightBox lb1 = new LightBox(client1, "Samochody");
        LightBox lb2 = new LightBox(client1, "Wyścigowe Samochody");
        LightBox lb3 = new LightBox(client2, "BMW");
        LightBox lb4 = new LightBox(client3, "Krajobrazy");


        lb1.add(product1);
        lb1.add(product2);
        lb1.add(product3);
        lb2.add(product1);
        lb3.add(product3);
        lb4.add(product1);
        lb4.add(product2);
        lb4.add(product3);

        repository.put(lb1);
        repository.put(lb2);
        repository.put(lb3);
        repository.put(lb4);

        printLightBoxes(repository.getFor(client1));

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
        for(Product product : lightBox) {
            System.out.println(String.format("%s%s | %s",
                    (product.isActive() ? "" : "X "),
                    product.getNumber(),
                    product.calculatePrice(lightBox.getOwner())));
        }
    }
}
