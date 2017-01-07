package pl.com.bottega.photostock.sales.presentation;

import java.util.Scanner;

public class MainScreen {

    private final Scanner scanner;
    private SearchScreen searchScreen;
    private ReservationScreen reservationScreen;
    private final OfferScreen offerScreen;

    public MainScreen(Scanner scanner, SearchScreen searchScreen, ReservationScreen reservationScreen, OfferScreen offerScreen) {
        this.searchScreen = searchScreen;
        this.reservationScreen = reservationScreen;
        this.offerScreen = offerScreen;
        this.scanner = scanner;
    }

    public void print() {
        while (true) {
            printMenu();
            String command = getCommand();
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        switch (command) {
            case "1":
                searchScreen.print();
                break;
            case "2":
                reservationScreen.print();
                break;
            case "3":
                offerScreen.print();
                break;
            default:
                System.out.println("Sorry nie rozumiem ;(");
        }
    }

    private void printMenu() {
        System.out.println("1. Wyszukaj produkty");
        System.out.println("2. Zarezerwuj produkt");
        System.out.println("3. Wygeneruj produkt");
    }

    private String getCommand() {
        System.out.print("Co chcesz robić: ");
        return scanner.nextLine();
    }
}
