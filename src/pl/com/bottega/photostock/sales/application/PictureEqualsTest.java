package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;

import java.util.HashSet;

public class PictureEqualsTest {
    public static void main(String[] args) {
        Picture picture1 = picture("123");
        Picture picture2 = picture(null);
        Picture picture3 = picture("123");
        Picture picture4 = picture("066");
        Picture picture5 = picture(null);

        System.out.println("Positive");
        System.out.println(picture1.equals(picture3));
        System.out.println(picture3.equals(picture1));
        System.out.println(picture1.equals(picture1));
        System.out.println(picture2.equals(picture2));
        System.out.println(picture2.equals(picture5));

        System.out.println("Negative");
        System.out.println(picture2.equals(null));
        System.out.println(picture1.equals(picture2));
        System.out.println(picture3.equals(picture4));
        System.out.println(picture2.equals(picture4));
    }

    private static Picture picture(String number){
        return new Picture(number, new HashSet<>(), Money.valueOf(100));
    }
}
