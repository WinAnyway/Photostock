package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;

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
        System.out.println("Witaj na ekranie lightboxów \nKomendy: pokaz, pokaz lightboxname, dodaj lightboxname productnumber, powrot");
        String clientNumber = loginScreen.getClient().getNumber();
        try {
            while (true) {
                String[] command = getCommand();
                String mainCommand = command[0];

                if (mainCommand.equals("pokaz"))
                    if (command.length == 2)
                        System.out.println(lightBoxManagement.getLightBox(clientNumber, command[1]));
                    else
                        System.out.println(lightBoxManagement.getLightBoxNames(clientNumber));

                else if (mainCommand.equals("dodaj") && command.length == 3) {
                    String lightBoxName = command[1];
                    String productNumber = command[2];
                    lightBoxManagement.addProduct(clientNumber, lightBoxName, productNumber);
                    System.out.println(String.format("Produkt %s został dodany do lightboxa %s", productNumber, lightBoxName));
                } else if (mainCommand.equals("powrot"))
                    return;
                else
                    System.out.println("Sorry nie rozumiem ;(");
            }
        }
        catch (IllegalStateException ex) {
            System.out.println("Najpierw dodaj lightboxy!");
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Wprowadź poprawne dane");
        }
    }

    private String[] getCommand() {
        return scanner.nextLine().trim().split(" ");
    }

}
