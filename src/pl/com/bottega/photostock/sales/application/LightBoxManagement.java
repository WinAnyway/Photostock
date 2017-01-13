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
            throw new ClientNotExistException(String.format("Client %s does not exist!", clientNumber));

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
        LightBox lightBox = lightBoxRepository.findLightBox(client, lightBoxName);
        if(!clientRepository.contains(clientNumber))
            throw new ClientNotExistException(String.format("Client %s does not exist!", clientNumber));
        if(lightBox == null)
            throw new LightBoxNotExistException(String.format("LightBox %s does not exist!", lightBoxName));

        return lightBox;
    }

    public void addProduct(String clientNumber, String lightBoxName, String productNumber) {
        if(!clientRepository.contains(clientNumber))
            throw new ClientNotExistException(String.format("Client %s does not exist!", clientNumber));
        if(productRepository.get(productNumber) == null)
            throw new ProductNotRecognizedException(String.format("I can't recognize product %s", productNumber));

        LightBox lightBox = lightBoxRepository.findLightBox(clientRepository.get(clientNumber), lightBoxName);

        if(lightBox == null) {
            lightBox = new LightBox(clientRepository.get(clientNumber), lightBoxName);
            lightBoxRepository.put(lightBox);
        }
        lightBox.add(productRepository.get(productNumber));
    }

}
