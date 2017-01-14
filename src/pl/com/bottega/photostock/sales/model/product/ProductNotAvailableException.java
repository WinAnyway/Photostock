package pl.com.bottega.photostock.sales.model.product;

public class ProductNotAvailableException extends RuntimeException {

    public ProductNotAvailableException(Product product) {
        super(String.format("Product %s is already in this reservation", product.getNumber()));
    }
}
