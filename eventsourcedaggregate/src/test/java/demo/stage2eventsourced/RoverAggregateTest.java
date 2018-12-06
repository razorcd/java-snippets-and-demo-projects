package demo.stage2eventsourced;

import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class RoverAggregateTest {

    @Test
    public void newRoverTest() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N);

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N));
    }

    @Test
    public void moveNFacingRoverForward() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N);
        roverAggregate.moveForward();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(2,2), RoverAggregate.Orientation.N));
    }

    @Test
    public void moveWFacingRoverForward() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.W);
        roverAggregate.moveForward();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,1), RoverAggregate.Orientation.W));
    }

    @Test
    public void moveSFacingRoverForward() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.S);
        roverAggregate.moveForward();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(0,2), RoverAggregate.Orientation.S));
    }

    @Test
    public void moveEFacingRoverForward() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.E);
        roverAggregate.moveForward();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,3), RoverAggregate.Orientation.E));
    }



    @Test
    public void turnNFacingRoverLeft() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N);
        roverAggregate.turnLeft();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.W));
    }

    @Test
    public void turnEFacingRoverLeft() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.E);
        roverAggregate.turnLeft();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N));
    }

    @Test
    public void turnSFacingRoverLeft() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.S);
        roverAggregate.turnLeft();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.E));
    }

    @Test
    public void turnWFacingRoverLeft() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.W);
        roverAggregate.turnLeft();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.S));
    }



    @Test
    public void turnNFacingRoverRight() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N);
        roverAggregate.turnRight();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.E));
    }

    @Test
    public void turnEFacingRoverRight() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.E);
        roverAggregate.turnRight();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.S));
    }

    @Test
    public void turnSFacingRoverRight() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.S);
        roverAggregate.turnRight();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.W));
    }

    @Test
    public void turnWFacingRoverRight() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.W);
        roverAggregate.turnRight();

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N));
    }


    @Test
    public void moveAndTurnRoverMultipleTimes1() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.W);

        roverAggregate.moveForward();  // 0,2 W
        roverAggregate.turnLeft(); // 0,2 S
        roverAggregate.moveForward(); // 0,1 S
        roverAggregate.turnRight(); // 0,1 W

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(0,1), RoverAggregate.Orientation.W));
    }

    @Test
    public void moveAndTurnRoverMultipleTimes2() {
        RoverAggregate roverAggregate = new RoverAggregate("id-01", new RoverAggregate.Location(3,1), RoverAggregate.Orientation.S);

        roverAggregate.turnLeft(); // 3,1 E
        roverAggregate.moveForward();  // 3,2 E
        roverAggregate.turnLeft(); // 3,2 N
        roverAggregate.moveForward();  // 4,2 N
        roverAggregate.turnRight(); // 4,2 E
        roverAggregate.turnRight(); // 4,2 S
        roverAggregate.moveForward(); // 3,2 S

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(3,2), RoverAggregate.Orientation.S));
    }

    @Test
    public void applyMoveAndTurnEventsMultipleTimes1() {
        Stream<DomainEvent> eventStream = Stream.of(
                new RoverCreatedEvent("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.W),
                new MovedForwardEvent(),
                new TurnedLeftEvent(),
                new MovedForwardEvent(),
                new TurnedRightEvent());

        RoverAggregate roverAggregate = eventStream.reduce(new RoverAggregate(), RoverAggregate::apply, (a, b)-> null);

        assertThat(roverAggregate).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(0,1), RoverAggregate.Orientation.W));
    }
}