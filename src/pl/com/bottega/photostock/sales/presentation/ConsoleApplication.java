package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.model.client.Address;
import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.client.ClientStatus;
import pl.com.bottega.photostock.sales.model.money.Money;
import pl.com.bottega.photostock.sales.model.product.Product;
import pl.com.bottega.photostock.sales.model.product.ProductRepository;
import pl.com.bottega.photostock.sales.model.purchase.Offer;
import pl.com.bottega.photostock.sales.model.purchase.Purchase;
import pl.com.bottega.photostock.sales.model.purchase.Reservation;

public class ConsoleApplication {

    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        Client client = new Client("Johny X", new Address(), Money.valueOf(100));
        Client vipClient = new ClientStatus.VIPClient("Johny VIP", new Address(), Money.ZERO, Money.valueOf(100));

        System.out.println(client.introduce());
        System.out.println(vipClient.introduce());
//        Reservation reservation = new Reservation(client);
        Reservation reservation = new Reservation(vipClient);

        Product product1 = productRepository.get("1");
        Product product2 = productRepository.get("2");
        Product product3 = productRepository.get("3");
        Product product4 = productRepository.get("4");

        reservation.add(product1);
        reservation.add(product2);
        reservation.add(product3);
        reservation.add(product4);
        System.out.println("After adding items count: " + reservation.getItemsCount());

        reservation.getItemsCount();

        Offer offer = reservation.generateOffer();

        boolean canAfford = vipClient.canAfford(offer.getTotalCost());
        System.out.println("Client can afford: " + String.valueOf(canAfford));

        if (canAfford) {
            vipClient.charge(offer.getTotalCost(), "Test purchase");
            Purchase purchase = new Purchase(vipClient, product1, product2, product3);
            System.out.println("Client purchased: " + purchase.getItemsCount() + " products");
            System.out.println("Total cost: " + offer.getTotalCost());
        } else {
            System.out.println("Client cannot afford");
        }
    }
}
