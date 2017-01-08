package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.model.Offer;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.Scanner;

public class OfferScreen {

    private final Scanner scanner;
    private final LoginScreen loginScreen;
    private final PurchaseProcess purchaseProcess;

    public OfferScreen(Scanner scanner, LoginScreen loginScreen, PurchaseProcess purchaseProcess) {

        this.scanner = scanner;
        this.loginScreen = loginScreen;
        this.purchaseProcess = purchaseProcess;
    }

    public void print() {
        String clientNumber = purchaseProcess.getReservation(loginScreen.getClient().getNumber());
        try {
            Offer offer = purchaseProcess.calculateOffer(clientNumber);
            printOffer(offer);
        }
        catch (IllegalStateException ex) {
            System.out.println("Nie ma aktywnych produktów w rezerwacji. Dodaj produkty!");
        }
    }

    private void printOffer(Offer offer) {
        System.out.println("Oferta specjalnie dla Ciebie:");
        int i = 1;
        for(Product product : offer.getItems()) {
            System.out.println(String.format("%d. %s", i++, product.getName()));
        }
        System.out.println(String.format("Zaledwie: %s", offer.getTotalCost()));
    }
}
