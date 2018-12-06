package demo.stage2eventsourced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoverRepository {

    private static final Map<String, List<DomainEvent>> storage = new HashMap<>();

    public void save(RoverAggregate rover) {
        List<DomainEvent> domainEvents = storage.getOrDefault(rover.getId(), new ArrayList<>());
        domainEvents.addAll(rover.getNewEvents());
        rover.clearNewEvents();

        storage.put(rover.getId(), domainEvents);
    }

    public RoverAggregate findById(String id) {
        return storage.get(id)
                .stream()
                .reduce(new RoverAggregate(), RoverAggregate::apply, (a, b) -> null);
    }
}
