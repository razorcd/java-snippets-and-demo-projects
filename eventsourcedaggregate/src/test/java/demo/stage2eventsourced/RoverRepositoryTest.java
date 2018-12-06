package demo.stage2eventsourced;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RoverRepositoryTest {

    @Test
    public void persistRover() {
        RoverAggregate rover = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N);

        RoverRepository roverRepository = new RoverRepository();
        roverRepository.save(rover);

        RoverAggregate savedRover = roverRepository.findById("id-01");
        assertThat(savedRover).isEqualTo(rover);
    }

    @Test
    public void overwritePersistedRover() {
        RoverRepository roverRepository = new RoverRepository();

        RoverAggregate rover = new RoverAggregate("id-01", new RoverAggregate.Location(1,2), RoverAggregate.Orientation.N);
        roverRepository.save(rover);

        rover.moveForward();
        roverRepository.save(rover);

        RoverAggregate savedRover = roverRepository.findById("id-01");
        assertThat(savedRover).isEqualTo(new RoverAggregate("id-01", new RoverAggregate.Location(2,2), RoverAggregate.Orientation.N));
    }
}