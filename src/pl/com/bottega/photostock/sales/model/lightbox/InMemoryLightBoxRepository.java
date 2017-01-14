package pl.com.bottega.photostock.sales.model.lightbox;

import pl.com.bottega.photostock.sales.model.client.Client;

import java.util.*;

public class InMemoryLightBoxRepository implements LightBoxRepository {

    private static final Map<Client, Collection<LightBox>> REPOSITORY = new HashMap<>();

    @Override
    public void put(LightBox l) {
        Client owner = l.getOwner();
        REPOSITORY.putIfAbsent(owner, new HashSet<>());
        Collection<LightBox> ownerLightBoxes = REPOSITORY.get(owner);
        ownerLightBoxes.add(l);
    }

    @Override
    public Collection<LightBox> getFor(Client client) {
        return REPOSITORY.get(client);
    }

    @Override
    public Collection<String> getLightBoxNames(Client client) {
        Collection<String> lightBoxNames = new LinkedList<>();
        Collection<LightBox> lightBoxes = REPOSITORY.get(client);
        if(lightBoxes != null)
            for(LightBox lb : lightBoxes)
                lightBoxNames.add(lb.getName());
        return lightBoxNames;
    }

    @Override
    public LightBox findLightBox(Client client, String lightBoxName) {
        Collection<LightBox> clientLightBoxes = REPOSITORY.get(client);

        if(clientLightBoxes == null)
            return null;

        for(LightBox lightBox : clientLightBoxes){
            if(lightBox.getName().equals(lightBoxName))
                return lightBox;
        }
        return null;
    }
}
