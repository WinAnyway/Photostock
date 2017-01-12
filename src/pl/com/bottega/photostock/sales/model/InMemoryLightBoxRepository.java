package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InMemoryLightBoxRepository implements LightBoxRepository {

    Map<Client, Collection<LightBox>> repository = new HashMap<>();

    @Override
    public void put(LightBox l) {
        Client owner = l.getOwner();
        repository.putIfAbsent(owner, new HashSet<>());
        Collection<LightBox> ownerLightBoxes = repository.get(owner);
        ownerLightBoxes.add(l);
    }

    @Override
    public Collection<LightBox> getFor(Client client) {
        return repository.get(client);
    }

    @Override
    public LightBox findLightBox(Client client, String lightBoxName) {
        Collection<LightBox> clientLightBoxes = repository.get(client);

        if(clientLightBoxes == null)
            return null;

        for(LightBox lightBox : clientLightBoxes){
            if(lightBox.getName().equals(lightBoxName))
                return lightBox;
        }
        return null;
    }
}
