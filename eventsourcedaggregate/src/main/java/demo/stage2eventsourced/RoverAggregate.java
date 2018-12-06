package demo.stage2eventsourced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "newEvents")
public class RoverAggregate {
    private String id;
    private Location location;
    private Orientation orientation;

    // represent events that were applied since last persistence of the aggregate
    private List<DomainEvent> newEvents = new ArrayList<>();

    public RoverAggregate() {
    }

    public RoverAggregate(String id, Location location, Orientation orientation) {
        this.apply(new RoverCreatedEvent(id,location,orientation));
    }

    public void moveForward() {
        this.apply(new MovedForwardEvent());
    }

    public void turnLeft() {
        this.apply(new TurnedLeftEvent());
    }

    public void turnRight() {
        this.apply(new TurnedRightEvent());
    }



    public RoverAggregate apply(RoverCreatedEvent roverCreatedEvent) {
        this.id = roverCreatedEvent.getId();
        this.location = roverCreatedEvent.getLocation();
        this.orientation = roverCreatedEvent.getOrientation();

        newEvents.add(roverCreatedEvent);
        return this;
    }

    public RoverAggregate apply(MovedForwardEvent movedForwardEvent) {
        this.location = this.location.forward(orientation);

        newEvents.add(movedForwardEvent);
        return this;
    }

    public RoverAggregate apply(TurnedLeftEvent turnedLeftEvent) {
        this.orientation = this.orientation.left();

        newEvents.add(turnedLeftEvent);
        return this;
    }

    public RoverAggregate apply(TurnedRightEvent turnedRightEvent) {
        this.orientation = this.orientation.right();

        newEvents.add(turnedRightEvent);
        return this;
    }

    public RoverAggregate apply(DomainEvent domainEvent) {
        switch (domainEvent.getType()) {
            case "demo.stage2eventsourced.RoverCreatedEvent":
                return this.apply((RoverCreatedEvent) domainEvent);
            case "demo.stage2eventsourced.MovedForwardEvent":
                return this.apply((MovedForwardEvent) domainEvent);
            case "demo.stage2eventsourced.TurnedLeftEvent":
                return this.apply((TurnedLeftEvent) domainEvent);
            case "demo.stage2eventsourced.TurnedRightEvent":
                return this.apply((TurnedRightEvent) domainEvent);
        }
        throw new IllegalStateException("Unprocessable DomainEvent "+domainEvent);
    }

    // used for removing temporary events after persistence
    public void clearNewEvents() {
        newEvents.clear();
    }


    @Value
    @AllArgsConstructor
    public static class Location {
        private final double latitude; //N-S
        private final double longitude; //E-W

        private Location forward(Orientation orientation) {
            switch (orientation) {
                case N:
                    return new Location(this.getLatitude()+1, this.getLongitude());
                case E:
                    return new Location(this.getLatitude()+0, this.getLongitude()+1);
                case S:
                    return new Location(this.getLatitude()-1, this.getLongitude()+0);
                case W:
                    return new Location(this.getLatitude()+0, this.getLongitude()-1);
            }
            throw new IllegalStateException("Unprocessable orientation "+orientation);
        }

    }

    public static enum Orientation {
        N("W","E"),
        S("E","W"),
        E("N","S"),
        W("S","N");

        private String left;
        private String right;

        Orientation(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public Orientation left() {
            return Orientation.valueOf(left);
        }

        public Orientation right() {
            return Orientation.valueOf(right);
        }
    }
}
