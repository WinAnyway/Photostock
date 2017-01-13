package pl.com.bottega.photostock.sales.model;

public class ProductNotRecognizedException extends RuntimeException{
    public ProductNotRecognizedException (String message) {
        super(message);
    }
}
