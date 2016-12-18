package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Clip;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.HashSet;

public class PictureEqualsTest {
    public static void main(String[] args) {
        Product product1 = picture("123");
        Product product2 = picture(null);
        Product product3 = picture("123");
        Product product4 = picture("066");
        Product product5 = picture(null);

        Product clip1 = clip("123");
        Product clip2 = clip("456");
        Product clip3 = clip("123");

        System.out.println("Positive");
        System.out.println(product1.equals(product3));
        System.out.println(product3.equals(product1));
        System.out.println(product1.equals(product1));
        System.out.println(product2.equals(product2));
        System.out.println(product2.equals(product5));
        System.out.println(clip1.equals(clip3));

        System.out.println("Negative");
        System.out.println(product2.equals(null));
        System.out.println(product1.equals(product2));
        System.out.println(product3.equals(product4));
        System.out.println(product2.equals(product4));
        System.out.println(clip1.equals(product1));
        System.out.println(clip1.equals(clip2));
    }

    private static Product clip(String number) {
        return new Clip(number, "", 500L, Money.valueOf(1));
    }

    private static Picture picture(String number){
        return new Picture(number, "", new HashSet<>(), Money.valueOf(100));
    }
}
