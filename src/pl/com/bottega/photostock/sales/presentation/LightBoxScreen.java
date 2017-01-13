package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.model.*;

import java.util.Scanner;

public class LightBoxScreen {

    Scanner scanner;
    LightBoxManagement lightBoxManagement;
    LoginScreen loginScreen;

    public LightBoxScreen(Scanner scanner, LightBoxManagement lightBoxManagement, LoginScreen loginScreen) {

        this.scanner = scanner;
        this.lightBoxManagement = lightBoxManagement;
        this.loginScreen = loginScreen;
    }

    public void print() {
        System.out.println("Witaj na ekranie lightboxów\nKomendy: pokaz, pokaz lightboxname, dodaj lightboxname productnumber, powrot");
        String clientNumber = loginScreen.getClient().getNumber();
        try {
            while (true) {
                String[] command = getCommand();
                String mainCommand = command[0];

                if (mainCommand.equals("pokaz"))
                    if (command.length == 2)
                        printLightBox(clientNumber, command[1]);
                    else
                        printClientLightBoxes(clientNumber);
                else if (mainCommand.equals("dodaj") && command.length == 3)
                    addProductToLightBox(clientNumber, command[1], command[2]);
                else if (mainCommand.equals("powrot"))
                    return;
                else
                    System.out.println("Sorry nie rozumiem ;(");
            }
        } catch (IllegalStateException ex) {
            System.out.println("Najpierw dodaj lightboxy!");
        } catch (ClientNotExistException ex) {
            System.out.println("Niepoprawny numer klienta!");
        } catch (LightBoxNotExistException ex) {
            System.out.println("Niepoprawna nazwa lightboxa!");
        } catch (ProductNotRecognizedException ex) {
            System.out.println("Niepoprawny numer produktu!");
        }
    }

    private void addProductToLightBox(String clientNumber, String lightBoxName, String productNumber) {
        lightBoxManagement.addProduct(clientNumber, lightBoxName, productNumber);
        System.out.println(String.format("Produkt %s został dodany do lightboxa %s", productNumber, lightBoxName));
    }

    private void printClientLightBoxes(String clientNumber) {
            System.out.println("Dostępne lightboxy:");
            for (String lightBoxName : lightBoxManagement.getLightBoxNames(clientNumber))
            System.out.println(lightBoxName);
    }

    private void printLightBox(String clientNumber, String lightBoxName) {
        LightBox lightBox = lightBoxManagement.getLightBox(clientNumber, lightBoxName);
        System.out.println(String.format("Lightbox %s:", lightBox.getName()));
        for (Product product : lightBox)
            System.out.println(product);

    }

    private String[] getCommand() {
        return scanner.nextLine().trim().split(" ");
    }

}
