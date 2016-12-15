package pl.com.bottega.photostock.sales.model;

import java.util.*;

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
}
