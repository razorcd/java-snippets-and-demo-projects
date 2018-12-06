package demo.stage1initial;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RoverTest {

    @Test
    public void newRoverTest() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N);

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N));
    }

    @Test
    public void moveNFacingRoverForward() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N);
        rover.moveForward();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(2,2), Rover.Orientation.N));
    }

    @Test
    public void moveWFacingRoverForward() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.W);
        rover.moveForward();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,1), Rover.Orientation.W));
    }

    @Test
    public void moveSFacingRoverForward() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.S);
        rover.moveForward();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(0,2), Rover.Orientation.S));
    }

    @Test
    public void moveEFacingRoverForward() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.E);
        rover.moveForward();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,3), Rover.Orientation.E));
    }



    @Test
    public void turnNFacingRoverLeft() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N);
        rover.turnLeft();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.W));
    }

    @Test
    public void turnEFacingRoverLeft() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.E);
        rover.turnLeft();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N));
    }

    @Test
    public void turnSFacingRoverLeft() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.S);
        rover.turnLeft();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.E));
    }

    @Test
    public void turnWFacingRoverLeft() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.W);
        rover.turnLeft();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.S));
    }



    @Test
    public void turnNFacingRoverRight() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N);
        rover.turnRight();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.E));
    }

    @Test
    public void turnEFacingRoverRight() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.E);
        rover.turnRight();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.S));
    }

    @Test
    public void turnSFacingRoverRight() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.S);
        rover.turnRight();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.W));
    }

    @Test
    public void turnWFacingRoverRight() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.W);
        rover.turnRight();

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.N));
    }


    @Test
    public void moveAndTurnRoverMultipleTimes1() {
        Rover rover = new Rover("id-01", new Rover.Location(1,2), Rover.Orientation.W);

        rover.moveForward();  // 0,2 W
        rover.turnLeft(); // 0,2 S
        rover.moveForward(); // 0,1 S
        rover.turnRight(); // 0,1 W

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(0,1), Rover.Orientation.W));
    }

    @Test
    public void moveAndTurnRoverMultipleTimes2() {
        Rover rover = new Rover("id-01", new Rover.Location(3,1), Rover.Orientation.S);

        rover.turnLeft(); // 3,1 E
        rover.moveForward();  // 3,2 E
        rover.turnLeft(); // 3,2 N
        rover.moveForward();  // 4,2 N
        rover.turnRight(); // 4,2 E
        rover.turnRight(); // 4,2 S
        rover.moveForward(); // 3,2 S

        assertThat(rover).isEqualTo(new Rover("id-01", new Rover.Location(3,2), Rover.Orientation.S));
    }
}