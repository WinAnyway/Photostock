package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.AuthenticationProcess;
import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.infrastructure.InMemoryClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.InMemoryReservationRepository;
import pl.com.bottega.photostock.sales.model.*;

import java.util.Scanner;

public class LightBoxMain {

    private MainScreen mainScreen;
    private SearchScreen searchScreen;
    private ReservationScreen reservationScreen;
    private OfferScreen offerScreen;
    private LoginScreen loginScreen;
    private LightBoxScreen lightBoxScreen;

    public LightBoxMain() {
        Scanner scanner = new Scanner(System.in);

        ProductRepository productRepository = new InMemoryProductRepository();
        ClientRepository clientRepository = new InMemoryClientRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();
        LightBoxRepository lightBoxRepository = new InMemoryLightBoxRepository();

        ProductCatalog productCatalog = new ProductCatalog(productRepository);
        AuthenticationProcess authenticationProcess = new AuthenticationProcess(clientRepository);
        PurchaseProcess purchaseProcess = new PurchaseProcess(clientRepository, reservationRepository, productRepository);
        LightBoxManagement lightBoxManagement = new LightBoxManagement(lightBoxRepository, productRepository, clientRepository);

        loginScreen = new LoginScreen(scanner, authenticationProcess);
        searchScreen = new SearchScreen(scanner, productCatalog, loginScreen);
        reservationScreen = new ReservationScreen(scanner, loginScreen, purchaseProcess);
        offerScreen = new OfferScreen(scanner, loginScreen, purchaseProcess);
        lightBoxScreen = new LightBoxScreen(scanner, lightBoxManagement, loginScreen);
        mainScreen = new MainScreen(scanner, searchScreen, reservationScreen, offerScreen, lightBoxScreen);
    }

    public void start() {
        loginScreen.print();
        mainScreen.print();
    }

    public static void main(String[] args) {
        new LightBoxMain().start();
    }
}
