package pl.com.bottega.photostock.sales.model;

public class ClientNotExistException extends RuntimeException{

    public ClientNotExistException(String message) {
        super(message);
    }
}
