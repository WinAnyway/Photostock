package pl.com.bottega.photostock.sales.model.purchase;

import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.money.Money;
import pl.com.bottega.photostock.sales.model.product.Product;

import java.util.*;

public class Offer {

    private List<Product> items;
    private Client client;

    public Offer(Client client, Collection<Product> items) {
        this.client = client;
        this.items = new LinkedList<>(items);
        sortProductsByPriceDesc();
    }

    public boolean sameAs(Offer other, Money money) {
        //TODO
        return true;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Money getTotalCost() {
        Money totalCost = Money.ZERO;
        for(Product item : items){
            totalCost = totalCost.add(item.calculatePrice(client));
        }
        return totalCost;
    }

    private void sortProductsByPriceDesc() {
        this.items.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                Money price1 = p1.calculatePrice(client);
                Money price2 = p2.calculatePrice(client);
                //return -price1.compareTo(price2); -> też będzie git malejąco
                return price2.compareTo(price1);
            }
        });
    }

    public List<Product> getItems() {
        return items;
    }
}
