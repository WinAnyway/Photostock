package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.model.client.Address;
import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.lightbox.LightBox;
import pl.com.bottega.photostock.sales.model.money.Money;
import pl.com.bottega.photostock.sales.model.product.Product;
import pl.com.bottega.photostock.sales.model.product.ProductRepository;

public class LightBoxTest {
    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        Product product1 = productRepository.get("1");
        Product product2 = productRepository.get("2");
        Product product3 = productRepository.get("3");

        Client client1 = new Client("Janek", new Address(), Money.ZERO);
        Client client2 = new Client("Franek", new Address(), Money.valueOf(10));

        LightBox lb1 = new LightBox(client1, "Samochody");
        LightBox lb2 = new LightBox(client1, "Wyścigowe Samochody");
        LightBox lb3 = new LightBox(client2, "BMW");

        lb1.add(product1);
        lb1.add(product2);
        lb1.add(product3);

        lb2.add(product1);

        lb3.add(product3);

        product1.deactivate();

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
        for(Product product : lightBox) {
            System.out.println(String.format("%s%s | %s | %s",
                    (product.isActive() ? "" : "X "),
                    product.getNumber(),
                    product.getName(),
                    product.calculatePrice(lightBox.getOwner())));
        }
    }
}
