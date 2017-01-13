package pl.com.bottega.photostock.sales.model;

public class LightBoxNotExistException extends RuntimeException {

    public LightBoxNotExistException (String message) {
        super(message);
    }
}
