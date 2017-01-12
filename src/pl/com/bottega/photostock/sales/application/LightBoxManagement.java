package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Collection;
import java.util.LinkedList;

public class LightBoxManagement {

    LightBoxRepository lightBoxRepository;
    ProductRepository productRepository;
    ClientRepository clientRepository;

    public LightBoxManagement(LightBoxRepository lightBoxRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.lightBoxRepository = lightBoxRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    public Collection<String> getLightBoxNames(String clientNumber) {
        if(!clientRepository.contains(clientNumber))
            throw new IllegalArgumentException(String.format("Client with number %s does not exist", clientNumber));

        Collection<String> lightBoxNames = new LinkedList<>();
        Collection<LightBox> lightBoxes = lightBoxRepository.getFor(clientRepository.get(clientNumber));

        if(lightBoxes == null)
            throw new IllegalStateException("There are no lightboxes to show!");

        for(LightBox lightBox : lightBoxes) {
            lightBoxNames.add(lightBox.getName());
        }
        return lightBoxNames;
    }

    public LightBox getLightBox(String clientNumber, String lightBoxName) {
        Client client = clientRepository.get(clientNumber);
        if(!clientRepository.contains(clientNumber))
            throw new IllegalArgumentException(String.format("Client with number %s does not exist", clientNumber));
        if(lightBoxRepository.findLightBox(client, lightBoxName) == null)
            throw new IllegalArgumentException(String.format("LightBox %s does not exist", lightBoxName));

        return lightBoxRepository.findLightBox(client, lightBoxName);
    }

    public void addProduct(String clientNumber, String lightBoxName, String productNumber) {
        if(!clientRepository.contains(clientNumber))
            throw new IllegalArgumentException(String.format("Client with number %s does not exist", clientNumber));
        if(productRepository.get(productNumber) == null)
            throw new IllegalArgumentException(String.format("I can't recognize product number %s", productNumber));

        LightBox lightBox = lightBoxRepository.findLightBox(clientRepository.get(clientNumber), lightBoxName);

        if(lightBox == null) {
            lightBox = new LightBox(clientRepository.get(clientNumber), lightBoxName);
            lightBoxRepository.put(lightBox);
            System.out.println(String.format("Stworzyłeś lightboxa %s", lightBoxName));
        }
        lightBox.add(productRepository.get(productNumber));
    }

}
