package demo.stage2eventsourced;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@RequiredArgsConstructor
public class RoverCreatedEvent extends DomainEvent implements Serializable {
    private final String id;
    private final RoverAggregate.Location location;
    private final RoverAggregate.Orientation orientation;
}
